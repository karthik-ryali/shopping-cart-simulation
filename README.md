# 🛒 Store Mart – Shop Management System

A console-based Retail Management System developed using **Java, JDBC, and MySQL**.  
This application allows sellers to manage products and customers to purchase items with automatic billing and stock updates.

---

## 📌 Project Overview

Store Mart is a role-based shop management system designed to handle:

- Product inventory management
- Customer purchasing and billing
- Seller administrative operations
- Real-time stock updates
- Order recording

The system demonstrates practical implementation of **JDBC connectivity, SQL operations, and structured programming in Java**.

---

## 🚀 Features

### 👨‍💼 Seller Module
- Add new products
- View all products
- Update product price and stock
- Delete products
- Password-protected access

### 🛍 Customer Module
- View available products
- Purchase multiple products
- Automatic bill generation
- Stock validation before purchase
- Order details stored in database

### 🧾 Billing System
- Calculates total amount dynamically
- Prevents purchase if stock is insufficient
- Updates inventory automatically
- Stores order history in `orders` table

---

## 🛠 Tech Stack

- **Language:** Java
- **Database:** MySQL
- **Connectivity:** JDBC
- **Driver:** MySQL Connector/J
- **Architecture:** Console-based role-driven application

---

## 🗄 Database Schema

### Database: `shop`

#### Table: `product`
| Column | Type |
|--------|------|
| id     | INT (Primary Key) |
| name   | VARCHAR |
| price  | DOUBLE |
| stock  | INT |

#### Table: `orders`
| Column | Type |
|--------|------|
| pid    | INT |
| pname  | VARCHAR |
| qty    | INT |
| total  | DOUBLE |

---

## ⚙️ Setup Instructions

1. Install MySQL Server.
2. Create database:
   ```sql
   CREATE DATABASE shop;
   USE shop;
   ```
3. Create required tables.
4. Update database credentials in the code:
   ```java
   DriverManager.getConnection(
       "jdbc:mysql://localhost:3306/shop",
       "root",
       "your_password"
   );
   ```
5. Add MySQL Connector JAR to project classpath.
6. Compile and run:
   ```bash
   javac com/shop/Shop.java
   java com.shop.Shop
   ```

---

## 🔐 Access Credentials

Seller Login Password:
```
1100
```

---

## 📂 Project Structure

```
|utils
|-> DBInitializer.java
|-> ProductManager.java
|-> PurchaseManager.java
|-> SecurityManager.java
|mysql-connector-j-8.0.33.jar
|Shop.java
|README.md
```

---

## 💡 Key Concepts Demonstrated

- JDBC Connection Handling
- PreparedStatement Usage
- ResultSet Processing
- SQL CRUD Operations
- Exception Handling
- Role-Based Console Navigation
- Real-Time Inventory Updates

---

## 📈 Future Improvements

- Implement transaction management
- Add user authentication system (username & password)
- Generate sales reports
- Add search and filter functionality
- Convert to Spring Boot REST API
- Implement layered architecture (DAO, Service, Model)

---

## 👨‍💻 Author

Developed as a practical database-driven Java application to simulate real-world retail store operations.

---

## 📄 License

This project is open-source and free to use for educational purposes.
