package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Empinsert {

	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
		
		CallableStatement cst=con.prepareCall("{call EmpInsert(?,?,?,?,?,?,?,?,?,?,?)");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter emp id: ");
		int empid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter empname: ");
		String empname=sc.nextLine();
		System.out.println("Enter empdesg:  ");
		String empdesg=sc.nextLine();
		System.out.println("Enter house no: ");
		String hno=sc.nextLine();
		System.out.println("Enter Street name: ");
		String sname=sc.nextLine();
		System.out.println("Enter city name: ");
		String city=sc.nextLine();
		System.out.println("Enter pincode: ");
		int pin=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter email id: ");
		String mail=sc.nextLine();
		System.out.println("Enter phno :");
		long phno=sc.nextLong();
		sc.nextLine();
		System.out.println("Enter basic sal: ");
		int bsal=sc.nextInt();
		System.out.println("enter total sal: ");
		int totsal=sc.nextInt();
		
		cst.setInt(1, empid);
		cst.setString(2, empname);
		cst.setString(3, empdesg);
		cst.setString(4, hno);
		cst.setString(5, sname);
		cst.setString(6, city);
		cst.setInt(7, pin);
		cst.setString(8, mail);
		cst.setLong(9, phno);
		cst.setInt(10, bsal);
		cst.setInt(11, totsal);
		
		cst.execute();
		System.out.println("inserted......");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/* above program procedure:
 * 		create or replace procedure EmpInsert(
    eid number,ename varchar2,edesg varchar2,hno varchar2, sname varchar2,city varchar2, pincode number,mailid varchar2, phno number, bsal number,totsal number)
    is
    begin
    insert into EmpData values(eid,ename,edesg);
    insert into EmpAddress values(eid,hno,sname,city,pincode);
    insert into EmpContact values(eid,mailid,phno);
    insert into Empsal values(eid,bsal,totsal);
    end;
/
 */
