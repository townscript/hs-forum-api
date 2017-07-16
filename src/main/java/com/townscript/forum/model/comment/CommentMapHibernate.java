package com.townscript.forum.model.comment;

import javax.persistence.*;

@Entity
@Table(name = "comment_map_table")
public class CommentMapHibernate {
	
	@Id @GeneratedValue
	@Column(name="COMMENT_ID")
	private Long commentId;
	
	@Column(name="TOPIC_ID")
	private Long topicId;
	
	@Column(name="USER_ID")
	private Long userId;
	
	public CommentMapHibernate() {
	}

	public CommentMapHibernate(Long commentId, Long topicId, Long userId) {
		super();
		this.commentId = commentId;
		this.topicId = topicId;
		this.userId = userId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
