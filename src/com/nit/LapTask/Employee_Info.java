package com.nit.LapTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class Employee12 {
	public static final Scanner sc = new Scanner(System.in);
	static Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			System.out.println("Connect");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static void insert() {
		try {
			PreparedStatement ps = con.prepareStatement("insert into Employee_Info values(?,?,?,?,?,?)");
			System.out.println("Enter employee id:  ");
			int empid = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter employee name: ");
			String empname = sc.nextLine();

			System.out.println("Enter employee salary: ");
			int empsal = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter employee address: ");
			String empaddress = sc.nextLine();

			System.out.println("Enter employee mail:");
			String empmail = sc.nextLine();

			System.out.println("Enter Employee phnum:");
			int phone = sc.nextInt();

			ps.setInt(1, empid);
			ps.setString(2, empname);
			ps.setInt(3, empsal);
			ps.setString(4, empaddress);
			ps.setString(5, empmail);
			ps.setInt(6, phone);
			int k = ps.executeUpdate();
			if (k > 0) {
				System.out.println("update Successfully.........");
			} else {
				System.out.println("Update faild.............");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static void retrive() {
		try {
			PreparedStatement ps1 = con.prepareStatement("select * from Employee_Info where empname like 'g%'");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4)
						+ " " + rs.getString(5) + " " + rs.getInt(6));
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static void delete() {
		try {
			int max_salary = 0;
			PreparedStatement ps2 = con.prepareStatement(
					"delete from Employee_Info where empsalary=(select min(empsalary) from Employee_Info)");
			int k = ps2.executeUpdate();
			if (k > 0) {
				System.out.println("delete succussfully.......");
			} else {
				System.out.println("delete failed.....");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}

public class Employee_Info {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean tr = true;
		while (tr) {
			System.out.println("Enter 1 for insert values into emp table:");
			System.out.println("Enter 2 for retrive employee whose name starts with 's':");
			System.out.println("Enter 3 for delete employee who is getting maximum salary. ");
			System.out.println("Enter 4 for Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Employee12.insert();
				break;
			case 2:
				Employee12.retrive();
				break;
			case 3:
				Employee12.delete();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input...");
			}
		}
	}

}
