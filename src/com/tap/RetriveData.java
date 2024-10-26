package com.tap;

import java.sql.*;
public class RetriveData {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		
		String query="select * from Employee1";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		System.out.println("_________________________________________________________");
		while(rs.next())
		{
			System.out.printf("| %-2d | %-12s | %-8d | %-10s | %-8s |\n",
					rs.getInt("empid"),
					rs.getString("empname"),
					rs.getLong("salary"),
					rs.getString("designation"),
					rs.getString("gender"));
		}
		System.out.println("_________________________________________________________");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
