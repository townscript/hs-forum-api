package com.townscript.forum.service.topic;

import java.util.Collection;

import com.townscript.forum.model.topic.TopicMapHibernate;

public interface TopicMapService {
		TopicMapHibernate getTopicMapByTopicId(long topicId);
		Collection<TopicMapHibernate> getAllTopicMaps();
}