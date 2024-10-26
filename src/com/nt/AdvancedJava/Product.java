package com.nt.AdvancedJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Product {

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");
            
            // Create the table if it doesn't exist
            Statement st = con.createStatement();
            //st.execute("create table product(Id_num number(3),pName varchar2(10),price number(10),qty number(3))");
			//System.out.println("table inserted");
            PreparedStatement pst = con.prepareStatement("INSERT INTO product VALUES(?,?,?,?)");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Enter product details:");
            System.out.print("ID: ");
            int id = sc.nextInt();
            pst.setInt(1, id);
            
            System.out.print("Name: ");
            String name = sc.next();
            pst.setString(2, name);
            
            System.out.print("Price: ");
            int price = sc.nextInt();
            pst.setInt(3, price);
            
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            pst.setInt(4, qty);
            
            // Execute the prepared statement to insert data into the table
            pst.executeUpdate();
            pst.close();
            System.out.println("Inserted");
            // Retrieve and display all products from the table
            Statement retrieveStatement = con.createStatement();
            ResultSet rs = retrieveStatement.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getInt(4));
            }
            retrieveStatement.close();
            
            // Close resources
            con.close();
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
