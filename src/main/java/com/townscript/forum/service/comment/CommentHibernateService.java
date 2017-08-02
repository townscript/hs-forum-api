package com.townscript.forum.service.comment;

import java.util.Collection;

import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.comment.CommentMapHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;

public interface CommentHibernateService 
{	
		long addComment(TopicHibernate topic, CommentHibernate comment, UserHibernate user);
		boolean submitVote(TopicHibernate topic, UserHibernate user, int voteValue);
		CommentMapHibernate getCommentMapByCommentId(long commentId);
		Collection<CommentMapHibernate> getAllCommentMaps();
		Collection<CommentHibernate> getAllComments();
		Collection<CommentMapHibernate> getCommentMapByTopicId(long topicId);
		CommentHibernate getCommentById(long commentId);
}