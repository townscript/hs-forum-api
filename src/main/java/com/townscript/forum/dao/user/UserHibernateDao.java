package com.townscript.forum.dao.user;


import java.util.Collection;
import com.townscript.forum.model.user.UserHibernate;

public interface UserHibernateDao {
	UserHibernate getUser(long id);
    Collection<UserHibernate> getAllUsers();
    UserHibernate getUserByUserNameAndPassword(String userName,String userPassword);
    boolean insertUser(UserHibernate user);
    boolean updateUser(UserHibernate user);
    boolean deleteUser(long id);
    
}