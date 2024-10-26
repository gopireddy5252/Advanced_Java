package com.batchProcessing;
/*
 * batch processing for at a time multiple modifications in databse
 * merits: less time, high perfomance(only time exexute for all queries)
 */

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import com.connection.DBConnection;

public class Queries {
	final static Connection con=DBConnection.getConnection();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		try(con) {
			Statement st=con.createStatement();
			System.out.println("Enter eid :");
			int eid=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter employee name: ");
			String name=sc.nextLine();
			System.out.println("Enter emp sal: ");
			int sal=Integer.parseInt(sc.nextLine());
			System.out.println("enter emp Address: ");
			String address=sc.nextLine();
			System.out.println("Enter new Address: ");
			String nAddress=sc.nextLine();
			st.addBatch("create table Employee3(eid number(5),empname varchar2(15),empsal number(7),address varchar2(18))");
			st.addBatch("insert into Employee3 values"
					+"("+eid+",'"+name+"',"+sal+",'"+address+"')");
			st.addBatch("update Employee3 set address='"+nAddress+"' where eid="+eid+"");
			st.executeBatch();
			System.out.println("Success");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
