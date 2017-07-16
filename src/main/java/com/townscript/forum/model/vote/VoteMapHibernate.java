package com.townscript.forum.model.vote;

import javax.persistence.*;

@Entity
@Table(name = "vote_map_table")
public class VoteMapHibernate {
	
	@Id @GeneratedValue
	@Column(name="TOPIC_ID")
	private Long topicId;

	@Id @GeneratedValue
	@Column(name="VOTE_VALUE")
	private Integer voteValue;
	
	@Column(name="USER_ID")
	private Long userId;
	
	public VoteMapHibernate() {
	}

	public VoteMapHibernate(Long topicId, Integer voteValue, Long userId) {
		super();
		this.topicId = topicId;
		this.voteValue = voteValue;
		this.userId = userId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Integer getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(Integer voteValue) {
		this.voteValue = voteValue;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
