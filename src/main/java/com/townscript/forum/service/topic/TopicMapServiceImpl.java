package com.townscript.forum.service.topic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.dao.topic.TopicMapHibernateDao;
import com.townscript.forum.model.topic.TopicMapHibernate;

@Service
@Transactional
public class TopicMapServiceImpl implements TopicMapService
{
	@Autowired
	private TopicMapHibernateDao topicMapDao;
	
	public TopicMapHibernateDao getTopicMapDao() {
		return topicMapDao;
	}

	public void setTopicMapDao(TopicMapHibernateDao topicMapDao) {
		this.topicMapDao = topicMapDao;
	}

	@Override
	public TopicMapHibernate getTopicMapByTopicId(long topicId)
	{
		return topicMapDao.getTopicMapByTopicId(topicId); 
	}
	
	@Override
	public Collection<TopicMapHibernate> getAllTopicMaps()
	{
		return topicMapDao.getAllTopicMaps();
	}
}
