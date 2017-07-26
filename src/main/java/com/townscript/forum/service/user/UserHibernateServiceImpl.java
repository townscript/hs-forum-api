package com.townscript.forum.service.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
	public long getUserIdByUserName(String userName)
	{
		return userDao.getUserIdByUserName(userName);
	}

	@Override
	public Collection<UserHibernate> getAllUsers() {
		// TODO Auto-generated method stub
			
		return userDao.getAllUsers();
	}
		
	@Override
	public boolean isUniqueUser(String username) {
		boolean isUnique = true;	
		Collection<UserHibernate> usersList = userDao.getAllUsers();
		
		for(UserHibernate user : usersList) {
			if(username.equals(user.getUserName())) {
				isUnique = false;
				break;
			}
		}
		
		return isUnique;
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
