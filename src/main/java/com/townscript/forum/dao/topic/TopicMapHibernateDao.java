package com.townscript.forum.dao.topic;

import com.townscript.forum.model.topic.TopicMapHibernate;

public interface TopicMapHibernateDao {
	boolean addTopicMap(TopicMapHibernate topicMap);
	boolean deleteTopicMap(long topicId);
}