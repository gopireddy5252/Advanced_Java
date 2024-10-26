package com.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.connection.DBConnection;

public class GetMetaData1 {

	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try (con){
			
			PreparedStatement ps=con.prepareStatement("select * from emp");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsm=rs.getMetaData();
			int count=rsm.getColumnCount();
			for(int i=1;i<=count;i++)
			{
				
				String name1=rsm.getColumnName(i);
				int type=rsm.getColumnType(i);
				String typename=rsm.getColumnTypeName(i);
				String tableName=rsm.getTableName(i);
				
				System.out.println("column names :"+name1);
				System.out.println("datatype size :"+type);
				System.out.println("column typeName: "+typename);
				System.out.println("table name :"+tableName);
				
			}
			while(rs.next())
			{
				String name=rs.getString(3);// if u want which column values then u put the colomn number
				System.out.println(name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
