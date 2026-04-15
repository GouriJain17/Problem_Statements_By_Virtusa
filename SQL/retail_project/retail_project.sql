-- Project: Online Retail Sales Analysis Database
-- Description: This project analyzes sales data using SQL queries
-- to generate business insights like revenue, customer value, and product performance.

CREATE DATABASE retail_db;
USE retail_db;

-- Customers table
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    city VARCHAR(50)
);

-- Products table
CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10,2)
);

-- Orders table
CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

-- Order_Items table with composite primary key
CREATE TABLE Order_Items (
    order_id INT,
    product_id INT,
    quantity INT,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

-- Insert Customers
INSERT INTO Customers VALUES
(1, 'Ravi', 'Hyderabad'),
(2, 'Sneha', 'Delhi'),
(3, 'Arjun', 'Mumbai'),
(4, 'Priya', 'Chennai');

SELECT * FROM Customers;

-- Insert Products
INSERT INTO Products VALUES
(101, 'Laptop', 'Electronics', 50000),
(102, 'Phone', 'Electronics', 20000),
(103, 'Shoes', 'Fashion', 3000),
(104, 'Watch', 'Accessories', 5000);

SELECT * FROM Products;

-- Insert Orders
INSERT INTO Orders VALUES
(1001, 1, '2024-01-10'),
(1002, 2, '2024-02-15'),
(1003, 1, '2024-03-05'),
(1004, 3, '2024-03-20');

SELECT * FROM Orders;

-- Insert Order Items
INSERT INTO Order_Items VALUES
(1001, 101, 1),
(1001, 103, 2),
(1002, 102, 1),
(1003, 104, 3),
(1004, 101, 1);

SELECT * FROM Order_Items;

-- Top Selling Products
SELECT p.name, SUM(oi.quantity) AS total_sold
FROM Order_Items oi
JOIN Products p ON oi.product_id = p.product_id
GROUP BY p.name
ORDER BY total_sold DESC;

-- Output:
-- Watch has highest sales (3 units), followed by Shoes and Laptop

-- Most Valuable Customers
SELECT c.name, SUM(p.price * oi.quantity) AS total_spent
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY c.name
ORDER BY total_spent DESC;

-- Output:
-- Ravi is the highest spending customer (₹71,000)

-- Monthly Revenue
SELECT MONTH(o.date) AS month, SUM(p.price * oi.quantity) AS revenue
FROM Orders o
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY MONTH(o.date)
ORDER BY month;

-- Output:
-- March generated the highest revenue among all months

-- Category-wise Sales
SELECT p.category, SUM(oi.quantity) AS total_sales
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.category;

-- Output:
-- Electronics and Accessories categories show strong sales

-- Inactive Customers
SELECT name
FROM Customers
WHERE customer_id NOT IN (
    SELECT DISTINCT customer_id FROM Orders
);

-- Output:
-- Priya is an inactive customer (no orders placed)