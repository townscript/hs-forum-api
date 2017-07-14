package com.townscript.forum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.townscript.forum.model.UserHibernate;
import com.townscript.forum.utility.ConnectionWithDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/townscript/forum/test-bean.xml")
public class UserHibernateServiceTest{

	String userNameTest1 = "hims1990";
	String passwordTest1 = "oolala";
	String userEmailTest1 = "himanshusingh20091@gmail.com";

	String userNameTest2 = "hims1990";
	String passwordTest2 = null;
	String userEmailTest2 = "himanshusingh20091@gmail.com";

	@Autowired
	private UserHibernateService userService;	
	

	public UserHibernateService getUserService() {
		return userService;
	}

	public void setUserService(UserHibernateService userService) {
		this.userService = userService;
	}

	@Before
	// function to clearup the session factory.
	public void cleanup() {
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
		Statement stmt = connection.createStatement();
		int i = stmt.executeUpdate("DELETE FROM user_data_table");
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void insertUserTest()
	{
        UserHibernate user = new UserHibernate();
		user.setUserName(userNameTest1);
		user.setPassword(passwordTest1);
		user.setUserEmail(userEmailTest1);

		if(userService.insertUser(user))
		{
			Connection connection = ConnectionWithDatabase.getConnection();

			try{
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_data_table WHERE USER_NAME=? AND USER_PASSWORD=?");
				ps.setString(1, userNameTest1);
				ps.setString(2, passwordTest1);

				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					assertEquals(userNameTest1, rs.getString("USER_NAME"));
					assertEquals(passwordTest1, rs.getString("USER_PASSWORD"));
					assertEquals(userEmailTest1, rs.getString("USER_EMAIL"));
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void getUserByUserNameAndPasswordTest()
	{
		Connection connection = ConnectionWithDatabase.getConnection();

		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user_data_table VALUES (1,?,?,?)");
			ps.setString(1, userNameTest1);
			ps.setString(2, userEmailTest1);
			ps.setString(3, passwordTest1);

			int i = ps.executeUpdate();

			UserHibernate user = userService.getUserByUserNameAndPassword(userNameTest1, passwordTest1);
			if(i==1){
				Integer id = new Integer(1);
				assertEquals(id,user.getUserId());
				assertEquals(userEmailTest1,user.getUserEmail());
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

	}

	@Test
	public void getUserTest()
	{
		Connection connection = ConnectionWithDatabase.getConnection();

		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user_data_table VALUES (1,?,?,?)");
			ps.setString(1, userNameTest1);
			ps.setString(2, userEmailTest1);
			ps.setString(3, passwordTest1);

			int i = ps.executeUpdate();

			UserHibernate user = userService.getUser(1);
			if(i==1){
				assertEquals(userNameTest1,user.getUserName());
				assertEquals(userEmailTest1,user.getUserEmail());
				assertEquals(passwordTest1,user.getPassword());
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void updateUserTest()
	{
		String userNameUpdateTest = "hims1991";
		String userEmailUpdateTest = "himanshu.singh@townscript.com";
		String userPasswordUpdateTest = "oolala1";
		Connection connection = ConnectionWithDatabase.getConnection();

		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user_data_table VALUES (1,?,?,?)");
			ps.setString(1, userNameTest1);
			ps.setString(2, userEmailTest1);
			ps.setString(3, passwordTest1);

			int i = ps.executeUpdate();
			
			UserHibernate user = new UserHibernate();
			user.setPassword(userPasswordUpdateTest);
			user.setUserEmail(userEmailUpdateTest);
			user.setUserName(userNameUpdateTest);
			user.setUserId(1);
			if(i==1&userService.updateUser(user)){
				PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM user_data_table WHERE USER_NAME=? AND USER_PASSWORD=?");
				ps1.setString(1, userNameUpdateTest);
				ps1.setString(2, userPasswordUpdateTest);

				ResultSet rs = ps1.executeQuery();
				if(rs.next())
				{
					assertEquals(1, rs.getInt("USER_ID"));
					assertEquals(userEmailUpdateTest, rs.getString("USER_EMAIL"));
					assertEquals(userNameUpdateTest, rs.getString("USER_NAME"));
					assertEquals(userPasswordUpdateTest, rs.getString("USER_PASSWORD"));
				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void deleteUserTest()
	{
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user_data_table VALUES (1,?,?,?)");
			ps.setString(1, userNameTest1);
			ps.setString(2, userEmailTest1);
			ps.setString(3, passwordTest1);

			int i = ps.executeUpdate();

			if(i==1 & userService.deleteUser(1))
			{
				PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM user_data_table WHERE USER_NAME=? AND USER_PASSWORD=?");
				ps1.setString(1, userNameTest1);
				ps1.setString(2, passwordTest1);

				ResultSet rs = ps1.executeQuery();
				assertFalse(rs.next());
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}	
}
