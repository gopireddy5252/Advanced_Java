package com.nt.AdvancedJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
			Statement stmt=con.createStatement();
			stmt.execute("create table employee(EmpNo number(3),Empname varchar2(10),sal number(10))");
			System.out.println("table created");
			
		}
		catch(Exception e)
		{
			System.out.println("hello");
		}
	}
}
