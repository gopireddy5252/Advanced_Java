package com.Blob;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;

public class RetrieveDataBlob {
	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from student_data where sid=101");
			ResultSet rs = ps.executeQuery();
			rs.next();
			InputStream inputStream= rs.getBinaryStream(2);
			FileOutputStream fio = new FileOutputStream("C:\\Users\\gopir\\OneDrive\\Desktop\\pics\\edit\\abcdefg.jpg");
			int k;
			while((k=inputStream.read())!=-1)
			{
				fio.write(k);
			}
			System.out.println("retrived..");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
