package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class BankAmount {
	private static final Scanner sc =new Scanner(System.in);
	private static Connection con;
	public static void main(String[] args) throws SQLException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		UpdateEmp.printUpdateData();
		transaction();
		con.setAutoCommit(false);
		// showing the table here updateemp is another class
		//UpdateEmp.printUpdateData();


	}
	public static  void transaction() throws SQLException
	{
		System.out.println("Enter sender: ");
		String sender=sc.nextLine();
		System.out.println("Enter reciever: ");
		String reciever=sc.nextLine();
		System.out.println("Enter amount: ");
		int amount=sc.nextInt();
		
		int x=UpdateBalance(sender,amount);
		int y=UpdateBalance(reciever,-amount);
		if(isConfirm(x,y))
		{
			con.commit();
		}
		else {
			{
				con.rollback();
			}
		}
		
		System.out.println("how many sender: "+x+"how many reciever: "+y);
	}
	
	private static int UpdateBalance(String user, int amt) throws SQLException {
		
		String query="update employee1 set salary=salary+? where name=?";
		PreparedStatement ps=con.prepareStatement(query);
		
		ps.setInt(1, amt);
		ps.setString(2, user);
		int result=ps.executeUpdate();
		return result;
	}
	
	private static boolean isConfirm(int x, int y) {
		System.out.println("do want wnat this trasaction??(yes/no)");
		String choice=sc.nextLine();
		return choice.equalsIgnoreCase("yes")&& x==1 && y==1 ;
	}

}
