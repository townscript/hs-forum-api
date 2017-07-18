package com.townscript.forum.dao.topic;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.constants.Constants;
import com.townscript.forum.model.topic.TopicHibernate;

public class TopicHibernateDaoImpl extends HibernateDaoSupport implements TopicHibernateDao{
	
	@Override
	public TopicHibernate getTopicById(long id) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM "+TopicHibernate.class.getName()+" WHERE topicId=:topicId";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", id);
			List<TopicHibernate> topicList = query.list();
			return topicList.iterator().next();	
			
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Collection<TopicHibernate> getAllTopics() {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			List<TopicHibernate> topicList = session.createQuery("FROM TopicHibernate").list();
			return topicList;
			
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public long createTopic(TopicHibernate topic) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().saveOrUpdate(topic);
			return topic.getTopicId();
			
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public String updateTopic(TopicHibernate topic) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().update(topic);
			return Constants.MSG_SUCCESS;
			
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}
		return Constants.MSG_FAIL;
	}

	@Override
	public String deleteTopicById(long id) {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		try {
			TopicHibernate topic = new TopicHibernate();
			topic.setTopicId(id);
			getHibernateTemplate().delete(topic);
			return Constants.MSG_SUCCESS;
		    
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}
		return Constants.MSG_FAIL;
	}

	/*@Override
	public Collection<TopicHibernate> getTopicsByUsername() {
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM "+TopicHibernate.class.getName()+" WHERE topicId=:topicId";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", id);
			List<TopicHibernate> topicList = query.list();
			return topicList.iterator().next();	
			
		} catch(HibernateException ex) {
			ex.printStackTrace();
		}

		return null;
	}*/
}
