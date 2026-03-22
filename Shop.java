// Shop.java
import java.sql.*;
import java.util.*;

import utils.ProductManager;
import utils.PurchaseManager;
import utils.SecurityManager;
import utils.DBInitializer;

public class Shop {

    // ------------- MAIN ---------------
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            DBInitializer.initialize();

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop",
                    "root",
                    "Root@123");

            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome To Store Mart..");

            while (true) {
                try {
                    System.out.println("1. Seller Login");
                    System.out.println("2. Customer Login");
                    System.out.println("3. exit");

                    int role = sc.nextInt();

                    if (role == 1) {

                        if (!SecurityManager.checkPass(sc)) {
                            System.out.println("Wrong password");
                            continue;
                        }

                        while (true) {
                            System.out.println("--- SELLER MENU ---");
                            System.out.println("1 Add");
                            System.out.println("2 View");
                            System.out.println("3 Update");
                            System.out.println("4 Delete");
                            System.out.println("5 Back");

                            int ch = sc.nextInt();

                            if (ch == 1)
                                ProductManager.addProduct(con, sc);
                            if (ch == 2)
                                ProductManager.viewProducts(con);
                            if (ch == 3)
                                ProductManager.updateProduct(con, sc);
                            if (ch == 4)
                                ProductManager.deleteProduct(con, sc);
                            if (ch == 5)
                                break;
                        }
                    }

                    if (role == 2) {

                        while (true) {
                            System.out.println("--- CUSTOMER MENU ---");
                            System.out.println("1 View Products");
                            System.out.println("2 Buy Products");
                            System.out.println("3 Back");

                            int ch = sc.nextInt();

                            if (ch == 1)
                                ProductManager.viewProducts(con);
                            if (ch == 2)
                                PurchaseManager.buyProduct(con, sc);
                            if (ch == 3)
                                break;
                        }
                    }

                    if (role == 3) {
                        System.out.println("Thanks For Visiting visit Again");
                        break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter numbers only.");
                    sc.nextLine();
                }
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Database Connection Error");
        }
    }
}
