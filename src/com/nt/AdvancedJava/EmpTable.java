package com.nt.AdvancedJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmpTable {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root") ;
		PreparedStatement pst=con.prepareStatement("insert into emp values(?,?,?,?) ");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Empid: ");
		int id=sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter EmpName: ");
		String name=sc.nextLine();
		
		
		System.out.println("Enter desg: ");
		String dsg=sc.nextLine();
		
		
		System.out.println("Enter salary: ");
		int sal=sc.nextInt();
		
		
		pst.setInt(2, id);
		pst.setString(1, name);
		pst.setString(3, dsg);
		pst.setInt(4, sal);
		
		int t=pst.executeUpdate();
		if(t>0)
		{
			System.out.println("values are inserted");
		}
		else
		{
			System.out.println("failed");
		}
		
		
		
	}
		catch(SQLException sqlEx) {
		    if (sqlEx.getErrorCode() == 1722) {
		        System.out.println("Invalid number value provided.");
		    } else {
		        System.out.println("SQL Exception occurred: " + sqlEx.getMessage());
		    }
		}

	}
}
