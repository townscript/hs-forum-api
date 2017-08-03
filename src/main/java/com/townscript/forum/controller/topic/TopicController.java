package com.townscript.forum.controller.topic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townscript.forum.constants.ErrorCodes;
import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.comment.CommentMapHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.topic.TopicMapHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.comment.CommentHibernateService;
import com.townscript.forum.service.topic.TopicMapService;
import com.townscript.forum.service.topic.TopicService;
import com.townscript.forum.service.user.UserHibernateService;
import com.townscript.forum.vo.CreateTopicVo;

//@ContextConfiguration(locations="/com/townscript/forum/main-bean.xml")
@RestController
@RequestMapping(value="/topic")
public class TopicController {
	
	private TopicService topicService;
	private UserHibernateService userService;
	private CommentHibernateService commentService;
	private TopicMapService topicMapService;
	
	public TopicController() {
		super();
        if (topicService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/forum/main-bean.xml");
                topicService = (TopicService) context
                                .getBean("TopicServiceImpl");
        }
        if(userService == null)
        {
        	ApplicationContext context = new ClassPathXmlApplicationContext(
                    "com/townscript/forum/main-bean.xml");
        	userService = (UserHibernateService) context
                    .getBean("UserHibernateServiceImpl");
        }
        if(commentService == null)
        {
        	ApplicationContext context = new ClassPathXmlApplicationContext(
                    "com/townscript/forum/main-bean.xml");
        	commentService = (CommentHibernateService) context
                    .getBean("CommentHibernateServiceImpl");
        }
        if (topicMapService == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                            "com/townscript/forum/main-bean.xml");
            topicMapService = (TopicMapService) context
                            .getBean("TopicMapServiceImpl");
        }
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/createTopic", method=RequestMethod.POST)
	public TopicHibernate createTopic(@RequestParam(value="dataJson") String topicJsonStr){
		CreateTopicVo createTopicVo = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			createTopicVo = mapper.readValue(topicJsonStr, CreateTopicVo.class);
			//String jsonInString = mapper.writeValueAsString(createTopicVo); //from Object to String
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		TopicHibernate topic=new TopicHibernate();
		topic.setTopicTitle(createTopicVo.getTitle());
		topic.setTopicDescription(createTopicVo.getDescription());
		
		UserHibernate user = new UserHibernate();
		user.setUserId(userService.getUserIdByUserName(createTopicVo.getUserName()));
		
		//TODO: topicUserMap table: set userId by userName for new topic entry
		//TODO: rollback logic
		//TODO: response entity
		
		long topicId = topicService.createTopic(topic,user);
		topic.setTopicId(topicId);
		
		return topic;
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getAllTopics", method=RequestMethod.GET)
	public String getAllTopics()
	{
		String message;
		
		try
		{
			JSONObject json = new JSONObject();
			json.put("status", "success");
			JSONArray array = new JSONArray();
			
			
			Collection<TopicHibernate> topicColl = topicService.getAllTopics();
			List<TopicHibernate> topicList = new ArrayList(topicColl);
			Collection<TopicMapHibernate> topicMapColl = topicMapService.getAllTopicMaps();
			List<TopicMapHibernate> topicMapList = new ArrayList(topicMapColl);
			if(topicList.size()==topicMapList.size())
			{
				for(int i=0;i<topicList.size();i++)
				{
					JSONObject item = new JSONObject();
					item.put("id", topicList.get(i).getTopicId());
					item.put("title", topicList.get(i).getTopicTitle());
					item.put("description", topicList.get(i).getTopicDescription());
					item.put("createdAt", topicList.get(i).getTopicDateTime());
					item.put("tags", topicList.get(i).getTopicTags());
					item.put("topicUrl", topicList.get(i).getTopicUrl());
					item.put("createdBy", userService.getUser(topicMapList.get(i).getUserId()).getUserName());
					item.put("upVoteCount", topicService.getVoteCountByTopicId(1, topicList.get(i).getTopicId()));
					item.put("downVoteCount", topicService.getVoteCountByTopicId(2, topicList.get(i).getTopicId()));
					
					Collection<CommentMapHibernate> commentMapColl = commentService.getCommentMapByTopicId(topicList.get(i).getTopicId());
					List<CommentMapHibernate> commentMapList = new ArrayList(commentMapColl);
					
					JSONArray arrayComm = new JSONArray();
					for(int j=0;j<commentMapList.size();j++)
					{
						JSONObject itemComm = new JSONObject();
						CommentHibernate comment = commentService.getCommentById(commentMapList.get(j).getCommentId());
						itemComm.put("id", comment.getCommentId());
						itemComm.put("type", comment.getCommentType());
						itemComm.put("value", comment.getCommentValue());
						itemComm.put("createdAt", comment.getCommentDateTime());
						itemComm.put("createdBy", userService.getUser(commentMapList.get(j).getUserId()).getUserName());
						itemComm.put("topicId", commentMapList.get(j).getTopicId());
						arrayComm.put(itemComm);
					}
					item.put("commentList", arrayComm);
					array.put(item);					
				}
				json.put("topicList", array);
				array = new JSONArray();
			}
			/*Collection<CommentHibernate> commentColl = commentService.getAllComments();
			List<CommentHibernate> commentList = new ArrayList(commentColl);
			
			if(commentList.size()==commentMapList.size())
			{
				for(int i=0;i<commentList.size();i++)
				{
					JSONObject item = new JSONObject();
					item.put("id", commentList.get(i).getCommentId());
					item.put("type", commentList.get(i).getCommentType());
					item.put("value", commentList.get(i).getCommentValue());
					item.put("createdAt", commentList.get(i).getCommentDateTime());
					item.put("createdBy", userService.getUser(commentMapList.get(i).getUserId()).getUserName());
					item.put("topicId", commentMapList.get(i).getTopicId());
					array.put(item);
				}
				json.put("commentList", array);
				array = null;
				
			}*/
			message = json.toString();
			return message;
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return ErrorCodes.FAIL;
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getTopicById", method=RequestMethod.POST)
	public TopicHibernate getTopicById(@RequestParam(value="topicId") long topicId) {
		TopicHibernate topic = null;
		try{
			topic = topicService.getTopicById(topicId);
		} catch(Exception e) {
			e.printStackTrace();
			return topic;
		}
		
		return topic;
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getTopicsByUserName", method=RequestMethod.POST)
	public Collection<TopicHibernate> getTopicsByUserName(@RequestParam(value="userName") String userName) {
		Collection<TopicHibernate> topicList = null;
		try{
			UserHibernate user = null;
			topicList = topicService.getTopicsByUsername(userName);
		} catch(Exception e) {
			e.printStackTrace();
			return topicList;
		}
		
		return topicList;
	}
	
}
