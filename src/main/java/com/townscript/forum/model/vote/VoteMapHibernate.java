package com.townscript.forum.model.vote;

import javax.persistence.*;

@Entity
@Table(name = "vote_map_table")
public class VoteMapHibernate {
	
	@Id @GeneratedValue
	@Column(name="VOTE_ID")
	private long voteId;
	
	@Column(name="TOPIC_ID")
	private long topicId;

	@Column(name="VOTE_VALUE")
	private int voteValue;
	
	@Column(name="USER_ID")
	private long userId;
	
	public VoteMapHibernate() {
	}

	public VoteMapHibernate(long topicId, int voteValue, long userId) {
		super();
		this.topicId = topicId;
		this.voteValue = voteValue;
		this.userId = userId;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public int getVoteValue() {
		return voteValue;
	}

	public void setVoteValue(int voteValue) {
		this.voteValue = voteValue;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
