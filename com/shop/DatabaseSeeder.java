package com.shop;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseSeeder {

    public static void initializeDatabase(Connection con) {
        try {
            Statement st = con.createStatement();
            
            // Create product table
            st.execute("CREATE TABLE IF NOT EXISTS product (" +
                       "id INT PRIMARY KEY, " +
                       "name VARCHAR(100), " +
                       "price DOUBLE, " +
                       "stock INT)");
            
            // Create orders table
            st.execute("CREATE TABLE IF NOT EXISTS orders (" +
                       "pid INT, " +
                       "pname VARCHAR(100), " +
                       "qty INT, " +
                       "total DOUBLE)");
            
            // Insert seed data if product table is empty
            ResultSet rs = st.executeQuery("SELECT count(*) FROM product");
            if (rs.next() && rs.getInt(1) == 0) {
                st.executeUpdate("INSERT INTO product VALUES(101, 'Laptop', 55000.0, 10)");
                st.executeUpdate("INSERT INTO product VALUES(102, 'Mouse', 500.0, 50)");
                st.executeUpdate("INSERT INTO product VALUES(103, 'Keyboard', 1500.0, 30)");
                System.out.println("Seeded database with sample products.");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error initializing database.");
            e.printStackTrace();
        }
    }
}
