package com.townscript.forum.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.dao.user.UserHibernateDao;
import com.townscript.forum.model.user.UserHibernate;

@Service
@Transactional
public class UserHibernateServiceImpl implements UserHibernateService{

	@Autowired
	private UserHibernateDao userDao;
	
	public UserHibernateDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserHibernateDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserHibernate getUser(int id) {
		// TODO Auto-generated method stub
	
		return userDao.getUser(id);
	}

	@Override
	public Set<UserHibernate> getAllUsers() {
		// TODO Auto-generated method stub
			
		return userDao.getAllUsers();
	}
		
	@Override
	public UserHibernate getUserByUserNameAndPassword(String userName, String userPassword) {
		// TODO Auto-generated method stub
	
		return userDao.getUserByUserNameAndPassword(userName, userPassword);		
	}

	@Override
	public boolean insertUser(UserHibernate user) {
		// TODO Auto-generated method stub
		
		return userDao.insertUser(user);
	}

	@Override
	public boolean updateUser(UserHibernate user) {
		// TODO Auto-generated method stub
		
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		
		return userDao.deleteUser(id);
	}
	

}
