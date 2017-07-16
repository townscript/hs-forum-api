package com.townscript.forum.dao.topic;

import java.util.Set;

import com.townscript.forum.model.topic.TopicHibernate;

public interface TopicHibernateDao {
	TopicHibernate getTopicById(long id);
    Set<TopicHibernate> getAllTopics();
    long insertTopic(TopicHibernate topic);
}