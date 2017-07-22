package com.townscript.forum.service.comment;

import static org.junit.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.Connection;
import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.utility.ConnectionWithDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/townscript/forum/test-bean.xml")
public class CommentJDBCBasedServiceTest{
	long topicId = 1;
	
	char commentType = 'T';
	String commentValue = "This was an amazing discussion";
	Date commentDateTime = new Date();
	
	long userId = 1;
	
	int voteValue = 1;
	
	@Autowired
	private CommentHibernateServiceImpl commentService;

	public CommentHibernateServiceImpl getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentHibernateServiceImpl commentService) {
		this.commentService = commentService;
	}
	
	@Before
	public void cleanup() {
		Connection connection = ConnectionWithDatabase.getConnection();
		try
		{
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM comment_data_table");
			i = stmt.executeUpdate("DELETE FROM vote_map_table");
			i = stmt.executeUpdate("DELETE FROM comment_map_table");
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Test
	public void addCommentTest()
	{
		TopicHibernate topic = new TopicHibernate();
		UserHibernate user = new UserHibernate();
		CommentHibernate comment = new CommentHibernate();
		
		topic.setTopicId(topicId);
		user.setUserId(userId);
		
		comment.setCommentType(commentType);
		comment.setCommentValue(commentValue);
		comment.setCommentDateTime(commentDateTime);
		long commentId = commentService.addComment(topic,comment,user);
		// Now read the comment by Id and check if it has been really added
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM comment_data_table WHERE COMMENT_ID="+commentId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				assertEquals(commentId, rs.getInt("COMMENT_ID"));
				char temp = rs.getString("COMMENT_TYPE").charAt(0);
				assertEquals(commentType, temp);
				assertEquals(commentValue, rs.getString("COMMENT_VALUE"));
			}
		}
		catch(SQLException ex)
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
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM vote_map_table WHERE TOPIC_ID=? AND USER_ID=?");
			ps.setLong(1, topicId);
			ps.setLong(2, userId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				assertEquals(topicId, rs.getInt("TOPIC_ID"));
				assertEquals(userId, rs.getInt("USER_ID"));
				assertEquals(voteValue, rs.getInt("VOTE_VALUE"));
			}			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
