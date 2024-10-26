package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;


public class EmpRetrieve {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
		
		CallableStatement cst=con.prepareCall("{call EmpRetrieve(?,?,?,?,?,?,?)}");
		System.out.println("Enter Emp id: ");
		int eid=sc.nextInt();
		cst.setInt(1, eid);
		
		cst.registerOutParameter(2, Types.VARCHAR);
		cst.registerOutParameter(3, Types.VARCHAR);
		cst.registerOutParameter(4, Types.VARCHAR);
		cst.registerOutParameter(5, Types.VARCHAR);
		cst.registerOutParameter(6, Types.VARCHAR);
		cst.registerOutParameter(7, Types.BIGINT);
		cst.execute();
		
		String ename=cst.getString(2);
		String edesg=cst.getString(3);
		String hno=cst.getString(4);
		String sname=cst.getString(5);
		String city=cst.getString(6);
		long pincode=cst.getLong(7);
		
		 System.out.println("Employee Details:");
         System.out.println("Employee ID: " + eid);
         System.out.println("Employee Name: " + ename);
         System.out.println("Employee Designation: " + edesg);
         System.out.println("House Number: " + hno);
         System.out.println("Street Name: " + sname);
         System.out.println("City: " + city);
         System.out.println("Pincode: " + pincode);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * CREATE OR REPLACE PROCEDURE EmpRetrieve(
    eid IN NUMBER,ename OUT VARCHAR2,edesg OUT VARCHAR2,hno OUT VARCHAR2,sname OUT VARCHAR2,city OUT VARCHAR2,pincode OUT NUMBER)
IS
BEGIN
    SELECT ename, edesg INTO ename, edesg FROM EmpData WHERE EmpData.eid = eid;
    SELECT hno, sname, city, pincode INTO hno, sname, city, pincode  FROM EmpAddress  WHERE EmpAddress.eid = eid;   
END;
/

 */
