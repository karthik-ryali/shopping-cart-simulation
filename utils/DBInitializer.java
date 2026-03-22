package utils;

import java.sql.*;

public class DBInitializer {

    public static void initialize() {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    "root",
                    "Root@123");

            Statement st = con.createStatement();

            st.executeUpdate("CREATE DATABASE IF NOT EXISTS shop");

            st.execute("USE shop");

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS product (" +
                            "id INT PRIMARY KEY," +
                            "name VARCHAR(50)," +
                            "price DOUBLE," +
                            "stock INT)");

            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS orders (" +
                            "oid INT AUTO_INCREMENT PRIMARY KEY," +
                            "pid INT," +
                            "pname VARCHAR(50)," +
                            "qty INT," +
                            "total DOUBLE)");

            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM product");
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                st.executeUpdate("INSERT INTO product VALUES (1,'Rice',50,100)");
                st.executeUpdate("INSERT INTO product VALUES (2,'Milk',30,200)");
                st.executeUpdate("INSERT INTO product VALUES (3,'Sugar',45,150)");
                st.executeUpdate("INSERT INTO product VALUES (4,'Oil',120,80)");

                System.out.println("Demo products inserted.");
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Database initialization failed");
            e.printStackTrace();
        }
    }
}