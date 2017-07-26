package com.townscript.forum.dao.topic;


import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.topic.TopicMapHibernate;

public class TopicMapHibernateDaoImpl extends HibernateDaoSupport implements TopicMapHibernateDao{
	
	@Override
	public boolean addTopicMap(TopicMapHibernate topicMap){
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			session.save(topicMap);
			return true;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteTopicMap(long topicId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			TopicMapHibernate topicMap = new TopicMapHibernate();
			topicMap.setTopicId(topicId);
			session.delete(topicMap);
			return true;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Collection<TopicMapHibernate> getTopicMapByUserId(long userId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			String queryString = "FROM TopicMapHibernate WHERE userId=:userId";
			Query query = session.createQuery(queryString);
			query.setParameter("userId", userId);
			Collection<TopicMapHibernate> topicMapList = query.list();
			return topicMapList;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
