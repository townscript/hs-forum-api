package com.townscript.forum.utility;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionWithDatabase {
	
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/forum_database";
	public static final String USER = "root";
	public static final String PASS = "";

	public static Connection getConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=(Connection) DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch(SQLException ex)
		{
			throw new RuntimeException("Error connecting to the database", ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error connecting to the database", e);
		}
		
	}
	
	public static void main(String[] args)
	{
		Connection connection = ConnectionWithDatabase.getConnection(); 
	}
}
