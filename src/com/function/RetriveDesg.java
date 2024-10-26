package com.function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import com.connection.DBConnection;

public class RetriveDesg {

	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try(con) {
			
			CallableStatement cst=con.prepareCall("{call ?:= RetirveDesg(?)}");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter emp id: ");
			int id=sc.nextInt();
			cst.setInt(2, id);
			cst.registerOutParameter(1, Types.VARCHAR);
			cst.execute();
			System.out.println("Emp id: "+id);
			System.out.println("desg is: "+cst.getString(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
		/*
		 * create or replace function RetirveDesg
			(eid number)return varchar2 as dsg varchar2(15);
			begin
			select Edesg into dsg from EmpData where eid=eid;
			return dsg;
			end;
			/
		 */
		// note: here whenever to return the String type then u should give datatype size also(varchar2(15)
		// this size must match the database colom size
		// whenever u return the int type value then not required give datatype size
