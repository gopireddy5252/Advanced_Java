package com.nt.AdvancedJava;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
class ConnectionDemo
{
public static void main(String args[])
{
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
System.out.println("Connection Established Successfully");
}
catch(ClassNotFoundException | SQLException e)
{
System.err.println(e);
}
}}
