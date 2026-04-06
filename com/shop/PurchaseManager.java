package com.shop;

import java.sql.*;

public class PurchaseManager {

    public static String buyProduct(Connection con, int id, int qty) {
        try {
            PreparedStatement select = con.prepareStatement("select name,price,stock from product where id=?");
            PreparedStatement update = con.prepareStatement("update product set stock = stock-? where id=?");

            select.setInt(1, id);
            ResultSet rs = select.executeQuery();

            if (!rs.next()) {
                return "No product with id " + id;
            }

            String name = rs.getString(1);
            double price = rs.getDouble(2);
            int stock = rs.getInt(3);

            if (stock < qty) {
                return "Only " + stock + " available!";
            }

            double total = price * qty;
            
            update.setInt(1, qty);
            update.setInt(2, id);
            update.executeUpdate();

            PreparedStatement ps1 = con.prepareStatement("insert into orders(pid,pname,qty,total) values(?,?,?,?)");
            ps1.setInt(1, id);
            ps1.setString(2, name);
            ps1.setInt(3, qty);
            ps1.setDouble(4, total);
            ps1.executeUpdate();

            ps1.close();
            rs.close();
            
            return "Successfully purchased " + qty + " of " + name + "\nTotal Cost: " + total;

        } catch (Exception e) {
            return "Error during purchase: " + e.getMessage();
        }
    }
}