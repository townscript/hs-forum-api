package com.townscript.forum.dao;

import java.util.Set;

import com.townscript.forum.model.UserHibernate;

public interface UserHibernateDao {
	UserHibernate getUser(int id);
    Set<UserHibernate> getAllUsers();
    UserHibernate getUserByUserNameAndPassword(String userName,String userPassword);
    boolean insertUser(UserHibernate user);
    boolean updateUser(UserHibernate user);
    boolean deleteUser(int id);
    
}