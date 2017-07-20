package com.townscript.forum.dao.user;


import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.user.UserHibernate;

public class UserHibernateDaoImpl extends HibernateDaoSupport implements UserHibernateDao{


//	public UserHibernateDaoImpl()
//	{
//		getHibernateTemplate().getSessionFactory().openSession();
//	}
	
	@Override
	public UserHibernate getUser(long id) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;		
		try{
//			tx = session.beginTransaction();
			String queryString = "FROM "+UserHibernate.class.getName()+" WHERE userId=:userId";
			Query query = session.createQuery(queryString);
			query.setParameter("userId", id);
			List<UserHibernate> userList = query.list();
			return userList.get(0);			
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Collection<UserHibernate> getAllUsers() {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;
		try{
//			tx = session.beginTransaction();
			Collection<UserHibernate> userList = session.createQuery("FROM UserHibernate").list();
			return userList;
		}
		catch(HibernateException ex)
		{
//			if(tx!=null)
//				tx.rollback();
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public UserHibernate getUserByUserNameAndPassword(String userName, String password) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;
		try{
//			tx = session.beginTransaction();
			String queryString = "FROM "+UserHibernate.class.getName()+" WHERE userName=:user AND password=:pass";
			Query query = session.createQuery(queryString);
			//query.addEntity("UserHibernate");
			query.setParameter("user", userName);
			query.setParameter("pass", password);
			List<UserHibernate> lists = query.list();
			
//			tx.commit();
			return lists.get(0);
            
		}
		catch(HibernateException ex)
		{
//			if(tx!=null)
//				tx.rollback();
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean insertUser(UserHibernate user) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;
		try{
//			tx = session.beginTransaction();
			getHibernateTemplate().save(user);
//			tx.commit();
			return true;
		}
		catch(HibernateException ex)
		{
//			if(tx!=null)
//				tx.rollback();
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(UserHibernate user) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;
		try{
//			tx = session.beginTransaction();
			getHibernateTemplate().update(user);
//			tx.commit();
			return true;		
		}
		catch(HibernateException ex)
		{
//			if(tx!=null)
//				tx.rollback();
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(long id) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;
		try
		{
//			tx = session.beginTransaction();
			UserHibernate user = new UserHibernate();
			user.setUserId(id);
			getHibernateTemplate().delete(user);
//			tx.commit();
		    return true;
		}
		catch(HibernateException ex)
		{
//			if(tx!=null)
//				tx.rollback();
			ex.printStackTrace();
		}
		return false;
	}
}
