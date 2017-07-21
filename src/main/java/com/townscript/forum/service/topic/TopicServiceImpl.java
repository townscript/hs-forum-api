package com.townscript.forum.service.topic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.dao.topic.TopicHibernateDao;
import com.townscript.forum.model.topic.TopicHibernate;

@Service
@Transactional
public class TopicServiceImpl extends HibernateDaoSupport implements TopicService{

	@Autowired
	private TopicHibernateDao topicDao;
	
	public TopicHibernateDao gettopicDao() {
		return topicDao;
	}

	public void settopicDao(TopicHibernateDao topicDao) {
		this.topicDao = topicDao;
	}
	
	@Override
	public long createTopic(TopicHibernate topic) {
		// TODO Auto-generated method stub
		return topicDao.createTopic(topic);
	}

	@Override
	public String updateTopic(TopicHibernate topic) {
		// TODO Auto-generated method stub
		return topicDao.updateTopic(topic);
	}

	@Override
	public String deleteTopicById(long topicId) {
		// TODO Auto-generated method stub
		return topicDao.deleteTopicById(topicId);
	}

	@Override
	public Collection<TopicHibernate> getAllTopics() {
		// TODO Auto-generated method stub
		return topicDao.getAllTopics();
	}

	@Override
	public Collection<TopicHibernate> getAllTopicsOnLoad() {
		// TODO Auto-generated method stub
		//need to modify it later: with resultset limit, etc
		return topicDao.getAllTopics();
	}

	@Override
	public TopicHibernate getTopicById(long topicId) {
		// TODO Auto-generated method stub
		return topicDao.getTopicById(topicId);
	}

	@Override
	public Collection<TopicHibernate> getTopicsByUsername(String userName) {
		// TODO Auto-generated method stub
		//return topicDao.getTopicsByUsername();
		//need to modify it: use userTopicMap table
		return null;
	}

}
