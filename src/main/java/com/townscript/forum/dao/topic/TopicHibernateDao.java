package com.townscript.forum.dao.topic;

import java.util.Collection;

import com.townscript.forum.model.topic.TopicHibernate;

public interface TopicHibernateDao {
	long createTopic(TopicHibernate topic);
    String updateTopic(TopicHibernate topic);
    String deleteTopicById(long id);
	TopicHibernate getTopicById(long id);
    Collection<TopicHibernate> getAllTopics();
    //Collection<TopicHibernate> getTopicsByUsername(String username);
}