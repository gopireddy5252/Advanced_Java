package com.Blob;
/*
 * 1.BLOB:
		BLOB stands for Binary Large OBjects and which is used to store
		Binary Stream data.
 */
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.connection.DBConnection;

public class InsertValues {

	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try {
		PreparedStatement ps=con.prepareStatement("insert into student_data values(?,?,?)");
		ps.setInt(1, 101);
		File f=new File("C:\\Users\\gopir\\OneDrive\\Desktop\\pics\\edit\\sketch1690453472393.jpg");
		FileInputStream fis=new FileInputStream(f);
		ps.setBytes(2, fis.readAllBytes());
		Date d=new Date(24,0,13);
		ps.setDate(3, d);
		int k=ps.executeUpdate();
		if(k>0)
		{
			System.out.println("values inserted..");
		}
		else {
			System.out.println("Inserteion fails....");
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
