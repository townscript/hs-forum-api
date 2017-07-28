package com.townscript.forum.service.topic;

import java.util.Collection;

import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;

public interface TopicService {
		long createTopic(TopicHibernate topic, UserHibernate user);
		String updateTopic(TopicHibernate topic);
	    String deleteTopicById(long id);
		Collection<TopicHibernate> getAllTopics();
		Collection<TopicHibernate> getAllTopicsOnLoad();
		TopicHibernate getTopicById(long topicId);
		Collection<TopicHibernate> getTopicsByUsername(String userName);
		long getVoteCountByTopicId(int voteValue, long topicId);
}