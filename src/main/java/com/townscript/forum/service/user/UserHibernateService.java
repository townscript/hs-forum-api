package com.townscript.forum.service.user;

import java.util.Collection;

import com.townscript.forum.model.user.UserHibernate;

public interface UserHibernateService 
{	
		UserHibernate getUser(int id);
		long getUserIdByUserName(String userName);
	    Collection<UserHibernate> getAllUsers();
	    UserHibernate getUserByUserNameAndPassword(String userName,String userPassword);
	    boolean insertUser(UserHibernate user);
	    boolean updateUser(UserHibernate user);
	    boolean deleteUser(int id);
		boolean isUniqueUser(String username);
}