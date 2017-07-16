package com.townscript.forum.model.topic;

import javax.persistence.*;

@Entity
@Table(name = "topic_data_table")
public class TopicHibernate {
	
	@Id @GeneratedValue
	@Column(name="TOPIC_ID")
	private Long topicId;
	
	@Column(name="TOPIC_TITLE")
	private String topicTitle;
	
	@Column(name="TOPIC_TAGS")
	private String topicTags;
	
	@Column(name="DATE_TIME_CREATED")
	private String topicDateTime;
	
	@Column(name="TOPIC_URL")
	private String topicUrl;
	
	@Column(name="TOPIC_DESCRIPTION")
	private String topicDescription;
	
	public TopicHibernate() {
	}

	public TopicHibernate(Long topicId, String topicTitle, String topicTags, String topicDateTime, String topicUrl,
			String topicDescription) {
		super();
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.topicTags = topicTags;
		this.topicDateTime = topicDateTime;
		this.topicUrl = topicUrl;
		this.topicDescription = topicDescription;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public String getTopicTags() {
		return topicTags;
	}

	public void setTopicTags(String topicTags) {
		this.topicTags = topicTags;
	}

	public String getTopicDateTime() {
		return topicDateTime;
	}

	public void setTopicDateTime(String topicDateTime) {
		this.topicDateTime = topicDateTime;
	}

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}
}
