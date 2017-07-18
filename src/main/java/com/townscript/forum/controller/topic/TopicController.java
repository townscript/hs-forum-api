package com.townscript.forum.controller.topic;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.TopicService;
import com.townscript.forum.service.UserHibernateService;
import com.townscript.forum.vo.CreateTopicVo;

//@ContextConfiguration(locations="/com/townscript/forum/main-bean.xml")
@RestController
@RequestMapping(value="/topic")
public class TopicController {
	
	private TopicService topicService;
	
	public TopicController() {
		super();
        if (topicService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/forum/main-bean.xml");
                topicService = (TopicService) context
                                .getBean("TopicServiceImpl");
        }
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/createTopic", method=RequestMethod.POST)
	public TopicHibernate createTopic(String topicJsonStr){
		
		CreateTopicVo createTopicVo = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			createTopicVo = mapper.readValue(topicJsonStr, CreateTopicVo.class);
			//String jsonInString = mapper.writeValueAsString(createTopicVo); //from Object to String
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		TopicHibernate topic=new TopicHibernate();
		topic.setTopicTitle(createTopicVo.getTitle());
		topic.setTopicDescription(createTopicVo.getDescription());
		
		//TODO: topicUserMap table: set userId by userName for new topic entry
		//TODO: rollback logic
		//TODO: response entity
		
		long topicId = topicService.createTopic(topic);
		topic.setTopicId(topicId);
		
		return topic;
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getAllTopics", method=RequestMethod.GET)
	public Collection<TopicHibernate> getAllTopics(){
		Collection<TopicHibernate> topicList = topicService.getAllTopics();
		return topicList;
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getTopicById", method=RequestMethod.POST)
	public TopicHibernate getTopicById(@RequestParam(value="topicId") long topicId) {
		TopicHibernate topic = topicService.getTopicById(topicId);
		return topic;
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getTopicsByUserName", method=RequestMethod.POST)
	public Collection<TopicHibernate> getTopicsByUserName(@RequestParam(value="userName") String userName) {
		Collection<TopicHibernate> topicList = topicService.getTopicsByUsername(userName);
		return topicList;
	}
	
	
}
