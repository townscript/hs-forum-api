package com.townscript.forum.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
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
public class CommentHibernateServiceImpl extends HibernateDaoSupport implements CommentHibernateService{

	@Autowired
	private CommentHibernateDao commentDao;
	@Autowired
	private CommentMapHibernateDao commentMapDao;
	@Autowired
	private VoteMapHibernateDao voteMapDao;
	
	public CommentHibernateDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentHibernateDao commentDao) {
		this.commentDao = commentDao;
	}

	public CommentMapHibernateDao getCommentMapDao() {
		return commentMapDao;
	}

	public void setCommentMapDao(CommentMapHibernateDao commentMapDao) {
		this.commentMapDao = commentMapDao;
	}

	public VoteMapHibernateDao getVoteMapDao() {
		return voteMapDao;
	}

	public void setVoteMapDao(VoteMapHibernateDao voteMapDao) {
		this.voteMapDao = voteMapDao;
	}
	
	@Override
	public long addComment(TopicHibernate topic, CommentHibernate comment, UserHibernate user)
	{
		// TODO Auto-generated method stub
		
		long commentId = commentDao.addComment(comment);
		comment.setCommentId(commentId);
		CommentMapHibernate commentMap = new CommentMapHibernate();
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
	public boolean submitVote(TopicHibernate topic, UserHibernate user, int voteValue)
	{
		VoteMapHibernate voteMap = new VoteMapHibernate();
		voteMap.setTopicId(topic.getTopicId());
		voteMap.setUserId(user.getUserId());
		voteMap.setVoteValue(voteValue);
		if(voteMapDao.getVoteByUserIdAndTopicId(user.getUserId(), topic.getTopicId())==null)
		{
			if(voteMapDao.addVoteMap(voteMap))
			{
				return true; //define error codes
			}
		}
		else if(voteMapDao.updateVoteMap(voteMap))
		{
			return true;
		}
		return false;
	}
}
