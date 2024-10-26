package com.function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class RetrieveEmp {
	static Connection con;
	static Scanner sc=new Scanner(System.in);
	public static void connect()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getTotalEmpcount() throws SQLException
	{
		CallableStatement cst=con.prepareCall("{call ?:=getTotalEmployees1}");
		cst.registerOutParameter(1,Types.INTEGER );
		cst.execute();
		System.out.println("total count :"+cst.getInt(1));
	}
	public static void callFunction() throws SQLException
	{
		CallableStatement cst=con.prepareCall("{call ?:=getTotalEmployees(?)}");
		System.out.println("Enter empid:");
		int empid=sc.nextInt();
		cst.setInt(2, empid);
		cst.registerOutParameter(1,Types.VARCHAR);
		cst.execute();
		
		System.out.println("Emp id: "+empid);
		System.out.println("Emp name="+cst.getString(1));
		
		
	}

	public static void main(String[] args) throws SQLException {
		connect();
		getTotalEmpcount();
		callFunction();

	}

}

		/*retrieving for emp name
		 * 
		 * create or replace function getTotalEmployees
			(eid number) return varchar2 as name varchar2(15);
			begin
			select empname into name from emp_info where empid=eid;
			return name;
			end;
			/
		*retrive the totalnumber of count 
		*
		*create or replace function getTotalEmployees1
		return number as totcount number;
		begin
		select count(*) into totcount from emp_info ;
		return totcount ;
		end;
		/
		 */
