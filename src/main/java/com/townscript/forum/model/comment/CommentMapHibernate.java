package com.townscript.forum.model.comment;

import javax.persistence.*;

@Entity
@Table(name = "comment_map_table")
public class CommentMapHibernate {
	
	@Id
	@Column(name="COMMENT_ID")
	private long commentId;
	
	@Column(name="TOPIC_ID")
	private long topicId;
	
	@Column(name="USER_ID")
	private long userId;
	
	public CommentMapHibernate() {
	}

	public CommentMapHibernate(long commentId, long topicId, long userId) {
		super();
		this.commentId = commentId;
		this.topicId = topicId;
		this.userId = userId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
