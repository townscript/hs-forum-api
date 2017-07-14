package com.townscript.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.townscript.forum.model.UserJdbc;
import com.townscript.forum.utility.ConnectionWithDatabase;


public class UserJdbcDaoImpl implements UserJdbcDao{

	@Override
	public UserJdbc getUser(int id) {
		// TODO Auto-generated method stub
		
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_data_table WHERE USER_ID="+id);
			
			if(rs.next())
			{
				return extractUserFromResultSet(rs);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	private UserJdbc extractUserFromResultSet(ResultSet rs) throws SQLException {
	    UserJdbc user = new UserJdbc();
	    
	    user.setUserId( rs.getInt("USER_ID") );
	    user.setUserName( rs.getString("USER_NAME") );
	    user.setUserEmail( rs.getString("USER_EMAIL") );
	    user.setPassword( rs.getString("USER_PASSWORD") );
	    
	    return user;
	}

	@Override
	public Set<UserJdbc> getAllUsers() {
		// TODO Auto-generated method stub
		Connection connection = ConnectionWithDatabase.getConnection();
		
		try{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_data_table");
			
			Set<UserJdbc> users = new HashSet<UserJdbc>();
			while(rs.next())
			{
				UserJdbc user = extractUserFromResultSet(rs);
				users.add(user);
			}
			return users;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public UserJdbc getUserByUserNameAndPassword(String user, String pass) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionWithDatabase.getConnection();
		
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_data_table WHERE USER_NAME=? AND USER_PASSWORD=?");
			ps.setString(1, user);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return extractUserFromResultSet(rs);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertUser(UserJdbc user) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionWithDatabase.getConnection();
		
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user_data_table VALUES (NULL,?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getPassword());
			
			int i = ps.executeUpdate();
			if(i==1){
				return true;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(UserJdbc user) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionWithDatabase.getConnection();
		
		try{
			PreparedStatement ps = connection.prepareStatement("UPDATE user_data_table SET USER_NAME=?, USER_EMAIL=?, USER_PASSWORD=? WHERE USER_ID=?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, (user.getUserId()).toString());
			int i=ps.executeUpdate();
			
			if(i==1)
			{
				return true;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		Connection connection = ConnectionWithDatabase.getConnection();
		try
		{
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM user_data_table WHERE USER_ID="+id);
			
			if(i==1)
			{
				return true;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	

}
