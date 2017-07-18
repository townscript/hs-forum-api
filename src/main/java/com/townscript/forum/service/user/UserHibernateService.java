package com.townscript.forum.service;

import java.util.Set;

import com.townscript.forum.model.user.UserHibernate;

public interface UserHibernateService 
{	
		UserHibernate getUser(int id);
	    Set<UserHibernate> getAllUsers();
	    UserHibernate getUserByUserNameAndPassword(String userName,String userPassword);
	    boolean insertUser(UserHibernate user);
	    boolean updateUser(UserHibernate user);
	    boolean deleteUser(int id);
}