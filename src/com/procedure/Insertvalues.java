package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Insertvalues {

	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
		
		CallableStatement cst=con.prepareCall("{call InsertData(?,?,?,?,?,?,?)}");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter accno: ");
		int accno=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Customer name: ");
		String cname=sc.nextLine();
		System.out.println("Enter balance: ");
		long bal=sc.nextLong();
		sc.nextLine();
		System.out.println("Enter account type: ");
		String acctype=sc.nextLine();
		System.out.println("enter address: ");
		String add=sc.nextLine();
		System.out.println("Enter mailid ");
		String mail=sc.nextLine();
		System.out.println("enter phno:");
		long phno=sc.nextLong();
		
		cst.setInt(1, accno);
		cst.setString(2, cname);
		cst.setLong(3, bal);
		cst.setString(4,acctype );
		cst.setString(5, add);
		cst.setString(6, mail);
		cst.setLong(7, phno);
		
		 cst.execute();
		 System.out.println("sucussfully");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
