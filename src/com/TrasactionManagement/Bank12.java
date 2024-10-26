package com.TrasactionManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.SQLException;
import java.util.Scanner;

import com.connection.DBConnection;

public class Bank12 {
    private static final Connection con = DBConnection.getConnection();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (con) {
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM emp_info WHERE empid = ?");
            PreparedStatement ps2 = con.prepareStatement("UPDATE emp_info SET empsalary = empsalary + ? WHERE empid = ?");
            con.setAutoCommit(false);
            System.out.println("Auto-commit status: " + con.getAutoCommit());

            Savepoint sp = con.setSavepoint();
            System.out.print("Enter your account number: ");
            int haccono = sc.nextInt();
            ps1.setInt(1, haccono);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                double bal = rs1.getDouble("empsalary");
                System.out.print("Enter beneficiary account number: ");
                int bAccono = sc.nextInt();
                System.out.print("Enter amount to be transferred: ");
                double amt = sc.nextDouble();

                if (amt <= bal) {
                    ps2.setDouble(1, -amt);
                    ps2.setInt(2, haccono);
                    int t1 = ps2.executeUpdate();

                    ps2.setDouble(1, amt);
                    ps2.setInt(2, bAccono);
                    int t2 = ps2.executeUpdate();

                    if (t1 == 1 && t2 == 1) {
                        con.commit();
                        System.out.println("Transaction successful.");
                    } else {
                        con.rollback(sp);
                        System.out.println("Transaction failed, rolling back.");
                    }
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}
