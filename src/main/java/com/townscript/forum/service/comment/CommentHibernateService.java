package com.townscript.forum.service.comment;

import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;

public interface CommentHibernateService 
{	
		long addComment(TopicHibernate topic, CommentHibernate comment, UserHibernate user);
		boolean submitVote(TopicHibernate topic, UserHibernate user, int voteValue);
}