package com.townscript.forum.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.forum.dao.comment.CommentHibernateDao;
import com.townscript.forum.dao.comment.CommentMapHibernateDao;
import com.townscript.forum.dao.vote.VoteMapHibernateDao;
import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.comment.CommentMapHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.model.vote.VoteMapHibernate;

@Service
@Transactional
public class CommentHibernateServiceImpl implements CommentHibernateService{

	@Autowired
	private CommentHibernateDao commentDao;
	private CommentMapHibernateDao commentMapDao;
	private CommentMapHibernate commentMap;
	private VoteMapHibernateDao voteMapDao;
	private VoteMapHibernate voteMap;
	
	@Override
	public long addComment(TopicHibernate topic, CommentHibernate comment, UserHibernate user){
		// TODO Auto-generated method stub
		
		long commentId = commentDao.addComment(comment);
		commentMap.setTopicId(topic.getTopicId());
		commentMap.setUserId(user.getUserId());
		commentMap.setCommentId(commentId);
		if(commentMapDao.addCommentMap(commentMap))
		{
			return commentId;
		}
		return 0;
	}

	@Override
	public String submitVote(TopicHibernate topic, UserHibernate user, Integer voteValue){
		
		String errorCode1 = "1001";
		if(voteMapDao.getVoteByUserIdAndTopicId(user.getUserId(), topic.getTopicId())==null)
		{
			voteMap.setTopicId(topic.getTopicId());
			voteMap.setUserId(user.getUserId());
			voteMap.setVoteValue(voteValue);
			if(voteMapDao.addVoteMap(voteMap))
			{
				return errorCode1; //define error codes
			}
		}
		else if(voteMapDao.updateVoteMap(voteMap))
		{
			return errorCode1;
		}
		return "CodeNotResponding";
	}
}
