package com.townscript.forum.service;

import java.util.Set;

import com.townscript.forum.dao.UserJdbcDaoImpl;
import com.townscript.forum.model.UserJdbc;


public class UserJdbcServiceImpl implements UserJdbcService{

	@Override
	public UserJdbc getUser(int id) {
		// TODO Auto-generated method stub
	
		UserJdbcDaoImpl userdaoimpl = new UserJdbcDaoImpl();
		return userdaoimpl.getUser(id);
	}

	@Override
	public Set<UserJdbc> getAllUsers() {
		// TODO Auto-generated method stub
			
		UserJdbcDaoImpl userdaoimpl = new UserJdbcDaoImpl();
		return userdaoimpl.getAllUsers();
	}
		
	@Override
	public UserJdbc getUserByUserNameAndPassword(String user, String pass) {
		// TODO Auto-generated method stub
	
		UserJdbcDaoImpl userdaoimpl = new UserJdbcDaoImpl();
		return userdaoimpl.getUserByUserNameAndPassword(user, pass);
		
	}

	@Override
	public boolean insertUser(UserJdbc user) {
		// TODO Auto-generated method stub
		
		UserJdbcDaoImpl userdaoimpl = new UserJdbcDaoImpl();
		return userdaoimpl.insertUser(user);
	}

	@Override
	public boolean updateUser(UserJdbc user) {
		// TODO Auto-generated method stub
		UserJdbcDaoImpl userdaoimpl = new UserJdbcDaoImpl();
		return userdaoimpl.updateUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		UserJdbcDaoImpl userdaoimpl = new UserJdbcDaoImpl();
		return userdaoimpl.deleteUser(id);
	}
	

}
