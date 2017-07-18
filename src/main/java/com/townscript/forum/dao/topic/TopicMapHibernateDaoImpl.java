package com.townscript.forum.dao.topic;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.townscript.forum.model.topic.TopicMapHibernate;

public class TopicMapHibernateDaoImpl extends HibernateDaoSupport implements TopicMapHibernateDao{
	
	@Override
	public boolean addTopicMap(TopicMapHibernate topicMap){
		// TODO Auto-generated method stub
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			getHibernateTemplate().save(topicMap);
			return true;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
}
