package com.shop;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ShopGUI {
    private Connection con;

    public ShopGUI(Connection con) {
        this.con = con;
        showMainMenu();
    }

    private void showMainMenu() {
        JFrame frame = new JFrame("Store Mart - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton sellerBtn = new JButton("1. Seller Login");
        JButton customerBtn = new JButton("2. Customer Login");
        JButton exitBtn = new JButton("3. Exit");

        frame.add(sellerBtn);
        frame.add(customerBtn);
        frame.add(exitBtn);

        sellerBtn.addActionListener(e -> {
            String passStr = JOptionPane.showInputDialog(frame, "Enter Password:");
            if (passStr != null) {
                try {
                    int pass = Integer.parseInt(passStr);
                    if (SecurityManager.checkPass(pass)) {
                        frame.dispose();
                        showSellerMenu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong Password!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Password Format!");
                }
            }
        });

        customerBtn.addActionListener(e -> {
            frame.dispose();
            showCustomerMenu();
        });

        exitBtn.addActionListener(e -> {
            System.exit(0);
        });

        // Add some padding
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showSellerMenu() {
        JFrame frame = new JFrame("Store Mart - Seller Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        JButton addBtn = new JButton("1. Add Product");
        JButton viewBtn = new JButton("2. View Products");
        JButton updateBtn = new JButton("3. Update Product");
        JButton deleteBtn = new JButton("4. Delete Product");
        JButton backBtn = new JButton("5. Back");

        frame.add(addBtn);
        frame.add(viewBtn);
        frame.add(updateBtn);
        frame.add(deleteBtn);
        frame.add(backBtn);

        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID:"));
                String name = JOptionPane.showInputDialog("Enter Name:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Price:"));
                int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter Stock:"));

                String result = ProductManager.addProduct(con, id, name, price, stock);
                JOptionPane.showMessageDialog(frame, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input Process Aborted!");
            }
        });

        viewBtn.addActionListener(e -> {
            String result = ProductManager.viewProducts(con);
            showProductsDialog(result);
        });

        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to update:"));
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter New Price:"));
                int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter New Stock:"));

                String result = ProductManager.updateProduct(con, id, price, stock);
                JOptionPane.showMessageDialog(frame, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input Process Aborted!");
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
                String result = ProductManager.deleteProduct(con, id);
                JOptionPane.showMessageDialog(frame, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input Process Aborted!");
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showCustomerMenu() {
        JFrame frame = new JFrame("Store Mart - Customer Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewBtn = new JButton("1. View Products");
        JButton buyBtn = new JButton("2. Buy Product");
        JButton backBtn = new JButton("3. Back");

        frame.add(viewBtn);
        frame.add(buyBtn);
        frame.add(backBtn);

        viewBtn.addActionListener(e -> {
            String result = ProductManager.viewProducts(con);
            showProductsDialog(result);
        });

        buyBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Product ID:"));
                int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity:"));

                String result = PurchaseManager.buyProduct(con, id, qty);
                JOptionPane.showMessageDialog(frame, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input Process Aborted!");
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showProductsDialog(String productsText) {
        JTextArea textArea = new JTextArea(productsText);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        JOptionPane.showMessageDialog(null, scrollPane, "Product List", JOptionPane.INFORMATION_MESSAGE);
    }
}
