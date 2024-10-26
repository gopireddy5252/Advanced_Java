package com.nt.AdvancedJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertValues {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
		Statement stmt=con.createStatement();
		//stmt.executeUpdate("insert into employee values(100,'ravi',50000)");
		//stmt.executeUpdate("insert into employee values(101,'ramu',60000)");
		//stmt.executeUpdate("delete from employee where Empno=100");
		stmt.executeUpdate("update employee set Empno=101 where Empname='ravi'");
		System.out.println("one row updated");
	}
		catch(Exception e)
		{
			System.out.println("hello");
		}
	}
}
