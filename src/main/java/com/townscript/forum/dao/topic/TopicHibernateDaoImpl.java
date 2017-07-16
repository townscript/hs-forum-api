package com.townscript.forum.dao.topic;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.topic.TopicHibernate;

public class TopicHibernateDaoImpl extends HibernateDaoSupport implements TopicHibernateDao{
	
	@Override
	public TopicHibernate getTopicById(long id) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM "+TopicHibernate.class.getName()+" WHERE topicId=:topicId";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", id);
			List<TopicHibernate> topicList = query.list();
			return topicList.get(0);			
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Set<TopicHibernate> getAllTopics() {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			List<TopicHibernate> topicList = session.createQuery("FROM TopicHibernate").list();
			
			Set<TopicHibernate> topics = new HashSet<TopicHibernate>();
			for(Iterator iterator = topicList.iterator();iterator.hasNext();)
			{
				TopicHibernate topic = (TopicHibernate)iterator.next();
				topics.add(topic);
			}
			return topics;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public long insertTopic(TopicHibernate topic) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().saveOrUpdate(topic);
			return topic.getTopicId();
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}
}
