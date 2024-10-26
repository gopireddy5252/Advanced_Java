package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertValues {
    private final static Scanner sc = new Scanner(System.in);
    static Connection con;
    static PreparedStatement ps = null;

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

            printCurrentData();

            String choice = null;
            String query = "INSERT INTO Employee1 VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(query);

            do {
                System.out.println("Enter empid: ");
                int empid = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Empname: ");
                String empname = sc.nextLine();
                System.out.println("Enter emp salary: ");
                Long sal = sc.nextLong();
                sc.nextLine();
                System.out.println("Enter designation: ");
                String desg = sc.nextLine();
                System.out.println("Enter emp gender: ");
                String gender = sc.nextLine();

                ps.setInt(1, empid);
                ps.setString(2, empname);
                ps.setLong(3, sal);
                ps.setString(4, desg);
                ps.setString(5, gender);
                
                //Data is collected from the user and added to the batch using ps.addBatch().
                //After the loop, ps.executeBatch() is called to insert all the data at once.
                ps.addBatch();

                System.out.println("Do you want to add more employees? (yes or no): ");
                choice = sc.nextLine();
            } while (choice.equalsIgnoreCase("yes"));

            int[] rows = ps.executeBatch();
            System.out.println("Number of rows updated: " + rows.length);
            
            printCurrentData();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static void printCurrentData() throws SQLException {
        String query = "SELECT * FROM Employee1";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        System.out.println("_________________________________________________________");

        while (rs.next()) {
            System.out.printf("| %-5d | %-12s | %-8d | %-10s | %-6s |\n", 
                rs.getInt("empid"), 
                rs.getString("empname"), 
                rs.getLong("salary"), 
                rs.getString("designation"), 
                rs.getString("gender"));
        }

        System.out.println("_________________________________________________________");
    }
}
