
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	private static Connection con;
	private DBConnection(){}
	static {
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
