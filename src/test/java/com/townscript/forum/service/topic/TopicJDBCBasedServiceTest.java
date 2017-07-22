package com.townscript.forum.service.topic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.Connection;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.utility.ConnectionWithDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/townscript/forum/test-bean.xml")
public class TopicJDBCBasedServiceTest{

	String topicTitle = "Bada hi Awesome Topic";
	String topicTags = "#awesome,#bada";
	Date topicDateTime = new Date();
	String topicUrl = "ABCD";
	String topicDescription = "ABCD";

	String topicTitle2 = "Bada hi Awesome Topic 2";
	String topicTags2 = "#awesome,#bada,#2";
	Date topicDateTime2 = new Date();
	String topicUrl2 = "ABCD2";
	String topicDescription2 = "ABCD2";

	long userId = 1;

	@Autowired
	private TopicServiceImpl topicService;	

	public TopicServiceImpl getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicServiceImpl topicService) {
		this.topicService = topicService;
	}

	@Before
	public void cleanup() {
		Connection connection = ConnectionWithDatabase.getConnection();
		try
		{
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM topic_data_table");
			i = stmt.executeUpdate("DELETE FROM topic_map_table");
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void createTopicTest()
	{
		TopicHibernate topic = new TopicHibernate();
		topic.setTopicDateTime(topicDateTime);
		topic.setTopicDescription(topicDescription);
		topic.setTopicTags(topicTags);
		topic.setTopicTitle(topicTitle);
		topic.setTopicUrl(topicUrl);

		UserHibernate user = new UserHibernate();
		user.setUserId(userId);

		long topicId = topicService.createTopic(topic,user);
		// Now read the comment by Id and check if it has been really added
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM topic_data_table WHERE TOPIC_ID="+topicId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				assertEquals(topicId, rs.getInt("TOPIC_ID"));
				assertEquals(topicTitle, rs.getString("TOPIC_TITLE"));
				assertEquals(topicDescription, rs.getString("TOPIC_DESCRIPTION"));
				assertEquals(topicUrl, rs.getString("TOPIC_URL"));
				assertEquals(topicTags, rs.getString("TOPIC_TAGS"));
			}

			ps = connection.prepareStatement("SELECT * FROM topic_map_table WHERE TOPIC_ID="+topicId);
			rs = ps.executeQuery();
			if(rs.next())
			{
				assertEquals(topicId, rs.getInt("TOPIC_ID"));
				assertEquals(userId, rs.getInt("USER_ID"));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void updateTopicTest()
	{
		//First we need to add a topic. Adding a topic below
		TopicHibernate topic = new TopicHibernate();

		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO topic_data_table VALUES (1,?,?,?,?,?)");
			ps.setString(1, topicTitle);
			ps.setString(2, topicTags);
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(topicDateTime);
			ps.setString(3, date);
			ps.setString(4, topicUrl);
			ps.setString(5, topicDescription);

			int i = ps.executeUpdate();

			ps = connection.prepareStatement("INSERT INTO topic_map_table VALUES (?,?)");
			ps.setLong(1, 1);
			ps.setLong(2, userId);

			i = ps.executeUpdate();

			//Now we will update the added topic
			String topicUpdateDescription = "XYZA";
			String topicUpdateTags = "#update,#useless";
			String topicUpdateTitle = "Created topic updated in the most useless way";
			String topicUpdateUrl = "XYZA";

			topic.setTopicId(1);
			topic.setTopicTags(topicUpdateTags);
			topic.setTopicDescription(topicUpdateDescription);
			topic.setTopicTitle(topicUpdateTitle);
			topic.setTopicUrl(topicUpdateUrl);

			String message = topicService.updateTopic(topic);

			// Now read the updated topic by Id and check if it has been really updated

			ps = connection.prepareStatement("SELECT * FROM topic_data_table WHERE TOPIC_ID="+1);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				assertEquals(1, rs.getInt("TOPIC_ID"));
				assertEquals(topicUpdateTitle, rs.getString("TOPIC_TITLE"));
				assertEquals(topicUpdateDescription, rs.getString("TOPIC_DESCRIPTION"));
				assertEquals(topicUpdateUrl, rs.getString("TOPIC_URL"));
				assertEquals(topicUpdateTags, rs.getString("TOPIC_TAGS"));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void deleteTopicTest()
	{
		//First we will add a topic and topic map
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO topic_data_table VALUES (1,?,?,?,?,?)");
			ps.setString(1, topicTitle);
			ps.setString(2, topicTags);
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(topicDateTime);
			ps.setString(3, date);
			ps.setString(4, topicUrl);
			ps.setString(5, topicDescription);

			ps.executeUpdate();
			ps = connection.prepareStatement("INSERT INTO topic_map_table VALUES (?,?)");
			ps.setLong(1, 1);
			ps.setLong(2, userId);

			ps.executeUpdate();

			//Now we will delete the added topic and see if it gets deleted
			topicService.deleteTopicById(1);

			// Now read the updated topic by Id and check if it has been really updated

			ps = connection.prepareStatement("SELECT * FROM topic_data_table WHERE TOPIC_ID="+1);
			ResultSet rs = ps.executeQuery();

			assertFalse(rs.next());
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void getAllTopicsTest()
	{
		//First we will add the two topics
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO topic_data_table VALUES (1,?,?,?,?,?)");
			ps.setString(1, topicTitle);
			ps.setString(2, topicTags);
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(topicDateTime);
			ps.setString(3, date);
			ps.setString(4, topicUrl);
			ps.setString(5, topicDescription);

			ps.executeUpdate();
			ps = connection.prepareStatement("INSERT INTO topic_map_table VALUES (?,?)");
			ps.setLong(1, 1);
			ps.setLong(2, userId);

			ps = connection.prepareStatement("INSERT INTO topic_data_table VALUES (2,?,?,?,?,?)");
			ps.setString(1, topicTitle2);
			ps.setString(2, topicTags2);
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = formatter.format(topicDateTime2);
			ps.setString(3, date);
			ps.setString(4, topicUrl2);
			ps.setString(5, topicDescription2);

			ps.executeUpdate();
			ps = connection.prepareStatement("INSERT INTO topic_map_table VALUES (?,?)");
			ps.setLong(1, 2);
			ps.setLong(2, userId);

			ps.executeUpdate();

			//Now read all the added topics
			Collection<TopicHibernate> topicColl = topicService.getAllTopics();
			List<TopicHibernate> topicList = new ArrayList(topicColl);
			assertEquals(1, topicList.get(0).getTopicId());
			assertEquals(topicTitle, topicList.get(0).getTopicTitle());
			assertEquals(topicDescription, topicList.get(0).getTopicDescription());
			assertEquals(topicUrl, topicList.get(0).getTopicUrl());
			assertEquals(topicTags, topicList.get(0).getTopicTags());

			assertEquals(2, topicList.get(1).getTopicId());
			assertEquals(topicTitle2, topicList.get(1).getTopicTitle());
			assertEquals(topicDescription2, topicList.get(1).getTopicDescription());
			assertEquals(topicUrl2, topicList.get(1).getTopicUrl());
			assertEquals(topicTags2, topicList.get(1).getTopicTags());
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	@Test
	public void getTopicByIdTest()
	{
		//First we will add the two topics
		Connection connection = ConnectionWithDatabase.getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO topic_data_table VALUES (1,?,?,?,?,?)");
			ps.setString(1, topicTitle);
			ps.setString(2, topicTags);
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(topicDateTime);
			ps.setString(3, date);
			ps.setString(4, topicUrl);
			ps.setString(5, topicDescription);

			ps.executeUpdate();
			ps = connection.prepareStatement("INSERT INTO topic_map_table VALUES (?,?)");
			ps.setLong(1, 1);
			ps.setLong(2, userId);
			
			//Now get topic by ID
			TopicHibernate topic = topicService.getTopicById(1);
			assertEquals(topicTitle, topic.getTopicTitle());
			assertEquals(topicTags, topic.getTopicTags());
			assertEquals(topicUrl, topic.getTopicUrl());
			assertEquals(topicDescription, topic.getTopicDescription());
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
