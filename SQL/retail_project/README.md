#  Online Retail Sales Analysis Database

##  Project Overview
The **Online Retail Sales Analysis Database** is a SQL-based project designed to analyze e-commerce sales data and generate meaningful business insights such as:

- Revenue analysis  
- Customer spending behavior  
- Product performance  
- Category-wise sales trends  
- Identification of inactive customers  

This project demonstrates the use of relational database design, SQL queries, joins, grouping, and aggregation to extract valuable insights from structured data.

---

##  Database Schema

### 1. Customers
- customer_id (Primary Key)  
- name  
- city  

### 2. Products
- product_id (Primary Key)  
- name  
- category  
- price  

### 3. Orders
- order_id (Primary Key)  
- customer_id (Foreign Key)  
- date  

### 4. Order_Items
- order_id (Foreign Key)  
- product_id (Foreign Key)  
- quantity  

**Primary Key:** (order_id, product_id)

---

##  Entity Relationship Diagram (ERD)

The ER diagram is included in this repository and represents relationships between tables:

- Customers → Orders (1:M)  
- Orders → Order_Items (1:M)  
- Products → Order_Items (1:M)  

---

##  Key Features & Queries

###  Top Selling Products
Identifies products with the highest quantity sold.

###  Most Valuable Customers
Finds customers who spent the most money.

###  Monthly Revenue Analysis
Calculates revenue generated per month.

###  Category-wise Sales
Analyzes performance of different product categories.

###  Inactive Customers
Finds customers who have never placed an order.

---

##  Sample Insights

- Watch is the top-selling product  
- Ravi is the highest spending customer (₹71,000)  
- March generated the highest revenue  
- Electronics and Accessories categories perform well  
- Priya is an inactive customer  

---

##  Technologies Used

- SQL (MySQL / RDBMS)  
- Relational Database Concepts  
- Data Analysis using SQL queries  

---


##  How to Run the Project

1. Open MySQL Workbench or any SQL client  
2. Run the SQL script (`retail_project.sql`)  
3. Execute queries step by step  
4. Analyze the outputs for insights  

---

##  Learning Outcomes

- Database design and normalization  
- Writing SQL queries with joins  
- Data aggregation and grouping  
- Business data analysis  
- Extracting insights from structured data  

---
