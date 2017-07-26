package com.townscript.forum.controller.topic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.townscript.forum.constants.Constants;
import com.townscript.forum.constants.ErrorCodes;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.topic.TopicService;
import com.townscript.forum.service.user.UserHibernateService;
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
        
        if (userService == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                            "com/townscript/forum/main-bean.xml");
            userService = (UserHibernateService) context
                            .getBean("UserHibernateServiceImpl");
        }
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/createTopic", method=RequestMethod.POST)
	public ResponseEntity<HttpResponseVo> createTopic(@RequestParam(value="title") String title,
			@RequestParam(value="description") String description,
			@RequestParam(value="tags") String tags,
			@RequestParam(value="userName") String userName){
		
		/*CreateTopicVo createTopicVo = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			createTopicVo = mapper.readValue(topicJsonStr, CreateTopicVo.class);
			//String jsonInString = mapper.writeValueAsString(createTopicVo); //from Object to String
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}*/
		
		TopicHibernate topic=new TopicHibernate();
		topic.setTopicTitle(title);
		topic.setTopicDescription(description);
		topic.setTopicTags(tags);
		topic.setTopicDateTime(new Date());
		//topic.setTopicUrl("");
		
		//TODO: rollback logic
		
		UserHibernate user = userService.getUserByUsername(userName);
		Long topicId = topicService.createTopic(topic,user);
		
		//TODO: create entry in user-topic-map table
		//Long topicUserMapId = topicService.createTopicMap(user.getId, topicId);
		//topic.setTopicId(topicId);
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.MSG_SUCCESS, topic, topicId), null, HttpStatus.OK);
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getAllTopics", method=RequestMethod.GET/*, produces=("application/json")*/)
	public ResponseEntity<HttpResponseVo> getAllTopics(/*@RequestParam(value="userName") String userName*/){
		Collection<TopicHibernate> topicList = null;
		try{
			//topicList = topicService.getAllTopics();
			TopicHibernate topic = new TopicHibernate();
			topic.setTopicDateTime(new Date());
			topic.setTopicTitle("title1");
			topic.setTopicTags("sports");
			topic.setTopicDescription("my topic");
			topicList= new ArrayList<TopicHibernate>();
			topicList.add(topic);
		
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.MSG_SUCCESS, topicList, null), null, HttpStatus.OK);
		//return "all topics";
	}
	
	@RequestMapping(value="/getDummyTopic", method=RequestMethod.GET)
	public String dummyMethod(){
		return "dummy topic";
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getTopicById", method=RequestMethod.POST)
	public ResponseEntity<HttpResponseVo> getTopicById(@RequestParam(value="topicId") long topicId) {
		TopicHibernate topic = null;
		try{
			topic = topicService.getTopicById(topicId);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.MSG_SUCCESS, topic, null), null, HttpStatus.OK);
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getTopicsByUserName", method=RequestMethod.POST)
	public ResponseEntity<HttpResponseVo> getTopicsByUserName(@RequestParam(value="userName") String userName) {
		Collection<TopicHibernate> topicList = null;
		try{
			UserHibernate user = null;
			topicList = topicService.getTopicsByUsername(userName);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.MSG_SUCCESS, topicList, null), null, HttpStatus.OK);
	}
	
}
