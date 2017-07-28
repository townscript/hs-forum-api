package com.townscript.forum.dao.topic;

import java.util.Collection;

import com.townscript.forum.model.topic.TopicMapHibernate;

public interface TopicMapHibernateDao {
	boolean addTopicMap(TopicMapHibernate topicMap);
	boolean deleteTopicMap(long topicId);
	Collection<TopicMapHibernate> getTopicMapByUserId(long userId);
	TopicMapHibernate getTopicMapByTopicId(long topicId);
	Collection<TopicMapHibernate> getAllTopicMaps();
}