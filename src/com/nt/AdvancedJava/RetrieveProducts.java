package com.nt.AdvancedJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrieveProducts {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load and register the JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection with the database
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");

            // Create a statement object
            stmt = con.createStatement();

            // Execute a query to retrieve all data from the product table
            rs = stmt.executeQuery("SELECT * FROM product");

            // Iterate through the result set and print each row
            System.out.println("Product Table Data:");
            while (rs.next()) {
                int id = rs.getInt("Id_num");
                String name = rs.getString("pName");
                int price = rs.getInt("price");
                int qty = rs.getInt("qty");
                System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + qty);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
