package com.townscript.forum.dao.topic;


import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.topic.TopicHibernate;
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
	
	@Override
	public TopicMapHibernate getTopicMapByTopicId(long topicId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			String queryString = "FROM TopicMapHibernate WHERE topicId=:topicId";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", topicId);
			List<TopicMapHibernate> topicMapList = query.list();
			return topicMapList.get(0);
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Collection<TopicMapHibernate> getAllTopicMaps()
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try
		{
			List<TopicMapHibernate> topicMapList = session.createQuery("FROM TopicMapHibernate").list();
			return topicMapList;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}
