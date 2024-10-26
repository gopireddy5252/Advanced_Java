package com.nit.LapTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
class Blc
{
	public static final Scanner sc=new Scanner(System.in);
	static Connection con;
	static {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
	} catch (Exception e) {
		System.out.println(e);
	}
	}
	public static void registation()
	{
		try {
			PreparedStatement ps = con.prepareStatement("Insert into StudentInfo values(?,?,?,?,?,?,?)");
			System.out.println("enter Rollno: ");
			int rollno = sc.nextInt();
			sc.nextLine();

			System.out.println("enter student name:");
			String name = sc.nextLine();

			System.out.println("Enter student percentage: ");
			int percentage = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter first name: ");
			String Fname = sc.nextLine();

			System.out.println("Enter last name: ");
			String Lname = sc.nextLine();

			System.out.println("enter student Email: ");
			String email = sc.nextLine();

			System.out.println("Enter Phone Number: ");
			long phnum = sc.nextLong();

			ps.setInt(1, rollno);
			ps.setString(2, name);
			ps.setInt(3, percentage);
			ps.setString(4, Fname);
			ps.setString(5, Lname);
			ps.setString(6, email);
			ps.setLong(7, phnum);
			int t=ps.executeUpdate();
			if(t>0)
			{
				System.out.println("update successfully.............");
			}
			else
			{
				System.out.println("update failed");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		}
	public static void login() {
	    try {
	        // Correct the SQL statement to use AND instead of commas
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM StudentInfo WHERE rollno = ? AND name = ?");
	        
	        System.out.println("enter rollno of student: ");
	        int rolnum = sc.nextInt();
	        sc.nextLine();
	        
	        System.out.println("enter name of student: ");
	        String name1 = sc.nextLine();
	        
	        ps.setInt(1, rolnum);
	        ps.setString(2, name1);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) { // Move the login check here to ensure it only happens if a record is found
	            System.out.println("Login successfully..");
	            boolean b = true;
	            while (b) {
	                System.out.println("enter 1 for show student who got above 60%");
	                System.out.println("enter 2 for update mailid and phone number based on rollno");
	                System.out.println("enter 3 for delete whose percentage 30% to 60%");
	                System.out.println("enter 4 for find how many students got more than 60%");
	                System.out.println("Enter a number: ");
	                int no = sc.nextInt();
	                switch (no) {
	                    case 1:
	                        abovesixty();
	                        break;
	                    case 2:
	                        update();
	                        break;
	                    case 3:
	                        delete();
	                        break;
	                    case 4:
	                        countStudent();
	                        break;
	                    default:
	                        System.out.println("invalid input");
	                }
	            }
	        } else {
	            System.out.println("Login failed. Invalid roll number or name.");
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
	private static void countStudent() {
		try {
			PreparedStatement ps1=con.prepareStatement("select*from StudentInfo where percentage>=60");
		ResultSet rs = ps1.executeQuery();
		int count=0;
		while(rs.next())
		{
				System.out.println(rs.getString(2));
				count++;
	}
		System.out.println("no.of students count:"+count);
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static void delete() {
		try {
			PreparedStatement ps = con
					.prepareStatement("delete from StudentInfo where percentage>30 and percentage<60");
			int k = ps.executeUpdate();
			if (k > 0) {
				System.out.println("delete succussfully.....");
			} else {
				System.out.println("delete failed.....");
			} 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static void update() {
		try {
			PreparedStatement ps=con.prepareStatement("Update StudentInfo set  GMAIL=?,phoneNum=?  where rollno=?");
			System.out.println("Enter MailId: ");
			String mailid=sc.next();
		
			
			System.out.println("Enter phNo: ");
			long phno=sc.nextLong();
			
			System.out.println("Enter rollnum: ");
			int rollno=sc.nextInt();
			
			ps.setString(1, mailid);
			ps.setLong(2, phno);
			ps.setInt(3, rollno);
			int k=ps.executeUpdate();
			if(k>0)
			{
				System.out.println("updated..........");
			}
			else
			{
				System.out.println("update failed..........");
			}
		
	} catch (Exception e) {
		System.out.println(e);
	}
	}
	private static void abovesixty() {
		try {
			PreparedStatement ps1 = con.prepareStatement("select*from StudentInfo where percentage>=60");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2));
			} 
		} catch (Exception e) {
			System.out.println(e);
		}
}
}
public class RegNDLog {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 for Registration & 2 for login :");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			Blc.registation();
			break;
		case 2:Blc.login();
			break;
		default:System.out.println("invalid input");
		}	
	}

}


