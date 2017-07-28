package com.townscript.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.dao.user.UserHibernateDao;
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
		
		String savedPassword = userDao.getUserByUserName(username).getPassword();
		
		if(password.equals(savedPassword)) {
			isValidLogin = true;
		}
		
		return isValidLogin;
	}

}
