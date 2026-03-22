// utils/PurchaseManager.java
package utils;

import java.sql.*;
import java.util.*;

public class PurchaseManager {

    // --------- BUY FUNCTION ----------
    public static void buyProduct(Connection con, Scanner sc) {
        try {
            System.out.print("How many different items to buy: ");
            int tot = sc.nextInt();

            double grandTotal = 0;

            PreparedStatement select = con.prepareStatement("select name,price,stock from product where id=?");

            PreparedStatement update = con.prepareStatement("update product set stock = stock-? where id=?");

            System.out.println("\n========= BILL =========");

            for (int i = 1; i <= tot; i++) {

                System.out.print("Enter product id: ");
                int id = sc.nextInt();

                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();

                select.setInt(1, id);
                ResultSet rs = select.executeQuery();

                if (!rs.next()) {
                    System.out.println("No product with id " + id);
                    continue;
                }

                String name = rs.getString(1);
                double price = rs.getDouble(2);
                int stock = rs.getInt(3);

                if (stock < qty) {
                    System.out.println("Only " + stock + " available!");
                    continue;
                }

                double total = price * qty;
                grandTotal += total;

                update.setInt(1, qty);
                update.setInt(2, id);
                update.executeUpdate();

                System.out.println(name + "  x" + qty + " = " + total);

                PreparedStatement ps1 = con.prepareStatement("insert into orders(pid,pname,qty,total) values(?,?,?,?)");

                ps1.setInt(1, id);
                ps1.setString(2, name);
                ps1.setInt(3, qty);
                ps1.setDouble(4, total);
                ps1.executeUpdate();

                ps1.close();
                rs.close();
            }

            System.out.println("------------------------");
            System.out.println("TOTAL BILL : " + grandTotal);
            System.out.println("========================");

        } catch (Exception e) {
            System.out.println("Error during purchase");
        }
    }
}
