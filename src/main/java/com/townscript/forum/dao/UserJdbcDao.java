package com.townscript.forum.dao;

import java.util.Set;

import com.townscript.forum.model.UserJdbc;

public interface UserJdbcDao {
	UserJdbc getUser(int id);
    Set<UserJdbc> getAllUsers();
    UserJdbc getUserByUserNameAndPassword(String user,String pass);
    boolean insertUser(UserJdbc user);
    boolean updateUser(UserJdbc user);
    boolean deleteUser(int id);
    
}