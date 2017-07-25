package com.townscript.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.dao.user.UserHibernateDao;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.LoginService;;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserHibernateDao userDao;
	
	public UserHibernateDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserHibernateDao userDao) {
		this.userDao = userDao;
	}

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
