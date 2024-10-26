package com.Clob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.connection.DBConnection;

/* CLOB:
 * CLOB stans for Character Large OBjects and which is used to Stores
	Character Stream data.

 */
public class Insertvalues {
	public static void main(String[] args) {
		Connection con=DBConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("insert into student_data1 values(?,?,?)");
			ps.setInt(1, 109);
			ps.setString(2, "gopi");
			File f=new File("C:\\Users\\gopir\\Downloads\\abc.docx");
			FileInputStream fr=new FileInputStream(f);
			ps.setBlob(3, fr);
			ps.executeUpdate();
			System.out.println("file inserted.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
