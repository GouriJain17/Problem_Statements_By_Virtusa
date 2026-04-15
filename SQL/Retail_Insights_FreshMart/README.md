# Retail Insights - FreshMart

## Overview
A SQL-based project to analyze supermarket inventory and sales data. It helps identify expiring products, dead stock, and revenue trends for better decision-making.

## Features
- Tracks product inventory and categories
- Identifies products nearing expiry
- Detects unsold (dead) stock
- Calculates category-wise revenue
- Finds top-selling and low-stock products

## Database Structure
- Categories
- Products
- SalesTransactions

## Reports
- Expiring Soon: Products expiring in next 7 days with stock > 50
- Dead Stock: Products not sold in last 60 days
- Revenue Analysis: Category-wise revenue for last month
- Top Selling Products: Based on quantity sold
- Low Stock: Products with stock ≤ 20

## Concepts Used
- Joins
- GROUP BY and SUM
- Date functions (CURDATE, DATE_ADD, DATE_SUB)
- Aggregation and sorting
