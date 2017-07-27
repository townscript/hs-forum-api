package com.townscript.forum.controller.topic;

import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townscript.forum.constants.Constants;
import com.townscript.forum.constants.ErrorCodes;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.topic.TopicService;
import com.townscript.forum.service.user.UserHibernateService;
import com.townscript.forum.vo.CreateTopicVo;
import com.townscript.forum.vo.HttpResponseVo;

//@ContextConfiguration(locations="/com/townscript/forum/main-bean.xml")
@RestController
@RequestMapping(value="/topic")
public class TopicController {
	
	private TopicService topicService;
	private UserHibernateService userService;
	
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
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/createTopic", method=RequestMethod.POST)
	public TopicHibernate createTopic(@RequestParam(value="data-json") String topicJsonStr){
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
	public Collection<TopicHibernate> getAllTopics(){
		Collection<TopicHibernate> topicList = null;
		try{
			topicList = topicService.getAllTopics();
		
		} catch(Exception e) {
			e.printStackTrace();
			return topicList;
		}
		
		return topicList;
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
