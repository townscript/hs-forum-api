package com.townscript.forum.model.topic;

import javax.persistence.*;

@Entity
@Table(name = "topic_map_table")
public class TopicMapHibernate {
	
	@Id
	@Column(name="TOPIC_ID")
	private Long topicId;
	
	@Column(name="USER_ID")
	private Long userId;
	
	public TopicMapHibernate() {
	}

	public TopicMapHibernate(Long topicId, Long userId) {
		super();
		this.topicId = topicId;
		this.userId = userId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
