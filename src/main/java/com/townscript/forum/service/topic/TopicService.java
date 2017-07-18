package com.townscript.forum.service;

import java.util.Collection;

import com.townscript.forum.model.topic.TopicHibernate;

public interface TopicService {
		long createTopic(TopicHibernate topic);
		String updateTopic(TopicHibernate topic);
	    String deleteTopicById(long id);
		Collection<TopicHibernate> getAllTopics();
		Collection<TopicHibernate> getAllTopicsOnLoad();
		TopicHibernate getTopicById(long topicId);
		Collection<TopicHibernate> getTopicsByUsername(String userName);
}