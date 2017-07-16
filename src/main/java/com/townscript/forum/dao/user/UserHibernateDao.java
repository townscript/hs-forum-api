package com.townscript.forum.dao.user;

import java.util.Set;

import com.townscript.forum.model.user.UserHibernate;

public interface UserHibernateDao {
	UserHibernate getUser(int id);
    Set<UserHibernate> getAllUsers();
    long getUserByUserNameAndPassword(String userName,String userPassword);
    boolean insertUser(UserHibernate user);
    boolean updateUser(UserHibernate user);
    boolean deleteUser(int id);
    
}