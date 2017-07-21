package com.townscript.forum.service.comment;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.model.vote.VoteMapHibernate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/townscript/forum/test-bean.xml")
public class CommentHibernateServiceTest extends HibernateDaoSupport{
	long topicId = 1;
	
	char commentType = 'T';
	String commentValue = "This was an amazing discussion";
	Date commentDateTime = new Date();
	
	long userId = 1;
	
	int voteValue = 1;
	
	@Autowired
	private CommentHibernateService commentService;

	public CommentHibernateService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentHibernateService commentService) {
		this.commentService = commentService;
	}
	
	@Before
	public void cleanup() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "DELETE FROM "+CommentHibernate.class.getName();
			Query query = session.createQuery(queryString);
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void addCommentTest()
	{
		TopicHibernate topic = new TopicHibernate();
		CommentHibernate comment = new CommentHibernate();
		UserHibernate user = new UserHibernate();
		
		topic.setTopicId(topicId);
		user.setUserId(userId);
		
		comment.setCommentType(commentType);
		comment.setCommentValue(commentValue);
		comment.setCommentDateTime(commentDateTime);
		
		long commentId = commentService.addComment(topic, comment, user);
		
		// Now read the comment by Id and check if it has been really added
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM CommentHibernate WHERE commentId=:commentId";
			Query query = session.createQuery(queryString);
			query.setParameter("commentId", commentId);
			List<CommentHibernate> commentList = query.list();
			
			assertEquals(commentId, commentList.get(0).getCommentId());
			assertEquals(commentType, commentList.get(0).getCommentType());
			assertEquals(commentValue, commentList.get(0).getCommentValue());
//			assertEquals(commentDateTime, commentList.get(0).getCommentDateTime());
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void submitVoteTest()
	{		
		TopicHibernate topic = new TopicHibernate();
		UserHibernate user = new UserHibernate();
		
		topic.setTopicId(topicId);
		user.setUserId(userId);
		
		commentService.submitVote(topic, user, voteValue);
		
		// Read the vote map table to see if the correct vote has been added
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		try{
			String queryString = "FROM VoteMapHibernate WHERE topicId=:topicId AND userId=:userId";
			Query query = session.createQuery(queryString);
			query.setParameter("topicId", topic.getTopicId());
			query.setParameter("userId", user.getUserId());
			List<VoteMapHibernate> voteMapList = query.list();
			
			assertEquals(topicId, voteMapList.get(0).getTopicId());
			assertEquals(userId, voteMapList.get(0).getUserId());
			assertEquals(voteValue, voteMapList.get(0).getVoteValue());
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
	}
}
