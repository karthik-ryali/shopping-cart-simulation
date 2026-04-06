package com.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop",
                    "root",
                    "Root@123"
            );

            // Initialize DB
            DatabaseSeeder.initializeDatabase(con);

            // Start CLI App
            startCLI(con);

        } catch (Exception e) {
            System.out.println("Database Connection Error");
            e.printStackTrace();
        }
    }

    public static void startCLI(Connection con) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== STORE MART ===");
            System.out.println("1. Seller Login");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sellerMenu(con, sc);
                    break;
                case 2:
                    customerMenu(con, sc);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= SELLER =================
    public static void sellerMenu(Connection con, Scanner sc) {
        System.out.print("Enter Password: ");
        int pass = sc.nextInt();

        if (!SecurityManager.checkPass(pass)) {
            System.out.println("Wrong Password!");
            return;
        }

        while (true) {
            System.out.println("\n--- SELLER MENU ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back");
            System.out.print("Choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    System.out.println(ProductManager.addProduct(con, id, name, price, stock));
                    break;

                case 2:
                    System.out.println(ProductManager.viewProducts(con));
                    break;

                case 3:
                    System.out.print("ID: ");
                    int uid = sc.nextInt();

                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();

                    System.out.print("New Stock: ");
                    int newStock = sc.nextInt();

                    System.out.println(ProductManager.updateProduct(con, uid, newPrice, newStock));
                    break;

                case 4:
                    System.out.print("ID: ");
                    int did = sc.nextInt();

                    System.out.println(ProductManager.deleteProduct(con, did));
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= CUSTOMER =================
    public static void customerMenu(Connection con, Scanner sc) {

        while (true) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. View Products");
            System.out.println("2. Buy Product");
            System.out.println("3. Back");
            System.out.print("Choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println(ProductManager.viewProducts(con));
                    break;

                case 2:
                    System.out.print("Product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    System.out.println(PurchaseManager.buyProduct(con, id, qty));
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}