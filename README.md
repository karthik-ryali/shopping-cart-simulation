# 🛒 Store Management System (Java + MySQL)

A simple **Store Management System** built using **Java (Swing GUI)** and **MySQL**.  
This project allows sellers to manage products and customers to purchase items with automatic stock updates.

---

## 📌 Features

### 👨‍💼 Seller (Admin)
- Add new products
- View all products
- Update product details (price, stock)
- Delete products
- Password-protected access

### 🛍️ Customer
- View available products
- Purchase products
- Automatic stock validation
- Displays success/failure messages

---

## 🗄️ Database Design

### Product Table
| Column | Type | Description |
|--------|------|------------|
| id | INT | Product ID (Primary Key) |
| name | VARCHAR | Product Name |
| price | DOUBLE | Product Price |
| stock | INT | Available Quantity |

### Orders Table
| Column | Type | Description |
|--------|------|------------|
| pid | INT | Product ID (Foreign Key) |
| pname | VARCHAR | Product Name |
| qty | INT | Quantity Purchased |
| total | DOUBLE | Total Cost |

---

## ⚙️ Technologies Used

- Java (JDK 8+)
- Java Swing (GUI)
- JDBC (Database Connectivity)
- MySQL (Database)
- SQL

---

## 🧠 System Workflow

1. User selects role (Seller / Customer)
2. Seller logs in using password
3. Seller manages products (CRUD operations)
4. Customer views products
5. Customer selects product and quantity
6. System checks stock availability
7. If valid:
   - Purchase is processed
   - Stock is updated
   - Order is stored
8. Else:
   - Error message shown

---

## 🛠️ Setup Instructions

1. Install MySQL and create database:
   ```sql
   CREATE DATABASE shop;
   ```
2. Create tables:

   ```sql

   CREATE TABLE product (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE,
    stock INT
   );

   CREATE TABLE orders (
    pid INT,
    pname VARCHAR(100),
    qty INT,
    total DOUBLE,
    FOREIGN KEY (pid) REFERENCES product(id)
   );

   ```

3. Update DB credentials in Shop.java:

   ```java
   Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/shop",
    "root",
    "your_password"
   );
   ```

Run the project:

   ```bash
   javac -cp ".;mysql-connector-j-8.0.33.jar" com/shop/*.java
   java -cp ".;mysql-connector-j-8.0.33.jar" com/shop/shop.java
   ```


## 🔐 Security
- Basic password authentication for seller access
- Prevents invalid transactions (e.g., insufficient stock)

## 🚀 Future Enhancements
- User authentication system (login/signup)
- Billing system with invoice generation
- Web-based version (HTML/CSS/JS + Backend)
- Search and filter products
- Reports and analytics dashboard

## 📚 References
Java Documentation – https://docs.oracle.com/javase
JDBC Documentation – https://docs.oracle.com/javase/tutorial/jdbc
MySQL Documentation – https://dev.mysql.com/doc
GeeksforGeeks – Java & JDBC Tutorials

## 👨‍💻 Author
R V S V KARTHIK

## 📌 Note

This project is developed as a college mini project to demonstrate concepts of:

1. Java programming
2. GUI development
3. Database integration
4. Basic system design