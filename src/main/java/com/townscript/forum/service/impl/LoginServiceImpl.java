package com.townscript.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.townscript.forum.dao.user.UserHibernateDao;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.LoginService;;

public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserHibernateDao userDao;
	
	public boolean checkLogin(String username, String password){
		boolean isValidLogin = false;
		
		UserHibernate user= userDao.getUserByUserName(username);
		
		String savedPassword = user.getPassword();
		
		if(password.equals(savedPassword)) {
			isValidLogin = true;
		}
		
		return isValidLogin;
	}

}
