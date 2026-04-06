package com.shop;

import java.sql.*;

public class ProductManager {

    public static String viewProducts(Connection con) {
        StringBuilder sb = new StringBuilder();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product");

            sb.append("--------------------------------------------------\n");
            sb.append(String.format("%-5s %-15s %-10s %-10s\n", "ID", "NAME", "PRICE", "STOCK"));
            sb.append("--------------------------------------------------\n");

            while (rs.next()) {
                sb.append(String.format("%-5d %-15s %-10.2f %-10d\n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)
                ));
            }

            sb.append("--------------------------------------------------\n");

            rs.close();
            st.close();

        } catch (Exception e) {
            return "Error viewing products: " + e.getMessage();
        }
        return sb.toString();
    }

    public static String addProduct(Connection con, int id, String name, double price, int stock) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into product values(?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, stock);
            ps.executeUpdate();
            ps.close();
            return "Product Inserted Successfully";
        } catch (Exception e) {
            return "Error adding product: " + e.getMessage();
        }
    }

    public static String deleteProduct(Connection con, int id) {
        try {
            PreparedStatement ps = con.prepareStatement("delete from product where id=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) return "Product Deleted";
            else return "No product found with id " + id;
        } catch (Exception e) {
            return "Error deleting product: " + e.getMessage();
        }
    }

    public static String updateProduct(Connection con, int id, double price, int stock) {
        try {
            PreparedStatement ps = con.prepareStatement("update product set price=?, stock=? where id=?");
            ps.setDouble(1, price);
            ps.setInt(2, stock);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) return "Product Updated";
            else return "No product found with id " + id;
        } catch (Exception e) {
            return "Error updating product: " + e.getMessage();
        }
    }
}
