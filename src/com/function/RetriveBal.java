package com.function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;

import com.connection.DBConnection;

public class RetriveBal {
	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try(con) {
			CallableStatement cst=con.prepareCall("{call ?:=RetriveBal(?)");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Account number: ");
			long accno=sc.nextLong();
			
			cst.setLong(2, accno);
			cst.registerOutParameter(1, Types.BIGINT);
			cst.execute();
			System.out.println("Account number: "+accno);
			System.out.println("balance: "+cst.getLong(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * create or replace function RetriveBal
	(ano number) return number as bal number;
	begin
	select balance into bal from BankAccount where Accno=ano;
	return bal;
	end;
	/

 */
