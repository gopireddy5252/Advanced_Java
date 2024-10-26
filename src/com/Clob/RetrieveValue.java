package com.Clob;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;

public class RetrieveValue {

	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from student_data1 where sid=109");
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
			InputStream binaryStream = rs.getBinaryStream(3);
			FileOutputStream fio = new FileOutputStream("C:\\TC\\res.docx");
			int k;
			
                while ((k = binaryStream.read())!= -1) {
                    fio.write( k);
                }
                rs.close();
                fio.close();
                
                System.out.println("File retrieved and saved successfully.");
			}
			else {
				System.out.println("File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
