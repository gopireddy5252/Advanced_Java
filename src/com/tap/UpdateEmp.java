package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateEmp {
	private final static Scanner sc=new Scanner(System.in);
	static Connection con;
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			 int choice=0;
			 
			 do {
				 printUpdateData();
			String query = "update Employee1 set salary=salary+? where empid=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			System.out.println("Enter much sal u want to increse: ");
			long sal=sc.nextLong();
			System.out.println("enter perciluar emp id: ");
			int id=sc.nextInt();
			ps.setLong(1,sal );
			ps.setInt(2, id);

			int k = ps.executeUpdate();

			if (k > 0) {
				System.out.println("update Successfully");
			} else {
					System.out.println("update failed....");
			} 
			
			
			System.out.println("do u want update more employee salaries: ");
			System.out.println("yes for enter 1, no for enter Except 1 ");
			 choice=sc.nextInt();
		}while(choice==1);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void printUpdateData()throws SQLException
	{
		String query="select * from Employee1";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		System.out.println("_________________________________________________________");
		while(rs.next())
		{
			
			System.out.printf("| %-2d | %-12s | %-8d | %-10s | %-8s |\n",rs.getInt("empid"),rs.getString("empname"),rs.getLong("salary"),rs.getString("designation"),rs.getString("gender"));
//			int empid=rs.getInt("empid");
//			String empname=rs.getString("empname");
//			long sal=rs.getLong("salary");
//			String desg=rs.getString("designation");
//			String gender=rs.getString("gender");
//			System.out.println(empid +"\t"+empname+"\t"+sal+"\t"+desg+"\t"+gender+"\t");
		}
		System.out.println("_________________________________________________________");
	}

}
