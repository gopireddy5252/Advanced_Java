package com.nit.LapTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
            System.out.println("Connected");

        
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5));
            }

            PreparedStatement ps1 = con.prepareStatement("UPDATE Student1 SET sid = ?,sname=?,smarks=? WHERE sid = ?");
            System.out.println("_______________To update values___________________");
            System.out.println("Enter new student id: ");
            int newStudentID = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter new Student name: ");
            String newStudentName=sc.nextLine();
            System.out.println("Enter new Student marks: ");
            int newStudentMarks=sc.nextInt();
            System.out.println("Enter the student id to be updated: ");
            int oldStudentID = sc.nextInt();

           
            ps1.setInt(1, newStudentID);
            ps1.setString(2, newStudentName);
            ps1.setInt(3, newStudentMarks);
            ps1.setInt(4, oldStudentID);

            int rowsUpdated = ps1.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful.....");
            } else {
                System.out.println("Update failed......");
            }

           
            ps1.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

