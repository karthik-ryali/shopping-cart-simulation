package com.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.SwingUtilities;

public class Shop {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop",
                    "root",
                    "Root@123"
            );

            // Seed DB tables and samples data
            DatabaseSeeder.initializeDatabase(con);

            // Start up Simple GUI App
            SwingUtilities.invokeLater(() -> new ShopGUI(con));

        } catch (Exception e) {
            System.out.println("Database Connection Error");
            e.printStackTrace();
        }
    }
}
