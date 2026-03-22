// utils/ProductManager.java
package utils;

import java.sql.*;
import java.util.*;

public class ProductManager {

    // --------- VIEW FUNCTION ----------
    public static void viewProducts(Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product");

            System.out.println("--------------------------------------");
            System.out.printf("%-5s %-15s %-10s %-10s\n",
                    "ID", "NAME", "PRICE", "STOCK");
            System.out.println("--------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-15s %-10.2f %-10d\n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4));
            }

            System.out.println("--------------------------------------");

            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Error viewing products");
        }
    }

    // --------- ADD FUNCTION ----------
    public static void addProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter id: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter price: ");
            double price = sc.nextDouble();

            System.out.print("Enter stock: ");
            int stock = sc.nextInt();

            PreparedStatement ps = con.prepareStatement("insert into product values(?,?,?,?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, stock);

            ps.executeUpdate();

            System.out.println("Product Inserted Successfully");

            ps.close();

        } catch (Exception e) {
            System.out.println("Error adding product");
        }
    }

    // --------- DELETE FUNCTION ----------
    public static void deleteProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter id to delete: ");
            int id = sc.nextInt();

            PreparedStatement ps = con.prepareStatement("delete from product where id=?");

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Product Deleted");
            else
                System.out.println("No product found with id " + id);

            ps.close();

        } catch (Exception e) {
            System.out.println("Error deleting product");
        }
    }

    // --------- UPDATE FUNCTION ----------
    public static void updateProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter id to update: ");
            int id = sc.nextInt();

            System.out.print("Enter new price: ");
            double price = sc.nextDouble();

            System.out.print("Enter new stock: ");
            int stock = sc.nextInt();

            PreparedStatement ps = con.prepareStatement("update product set price=?, stock=? where id=?");

            ps.setDouble(1, price);
            ps.setInt(2, stock);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Product Updated");
            else
                System.out.println("No product found with id " + id);

            ps.close();

        } catch (Exception e) {
            System.out.println("Error updating product");
        }
    }
}
