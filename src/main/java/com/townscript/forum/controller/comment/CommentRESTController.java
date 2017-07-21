package com.townscript.forum.controller.comment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townscript.forum.constants.Constants;
import com.townscript.forum.constants.ErrorCodes;
import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.topic.TopicHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.comment.CommentHibernateService;
import com.townscript.forum.utility.valueObject.comment.CommentVo;
import com.townscript.forum.utility.valueObject.topic.TopicVo;
import com.townscript.forum.utility.valueObject.user.UserVo;
import com.townscript.forum.utility.valueObject.vote.VoteVo;
import com.townscript.forum.vo.HttpResponseVo;


//@ContextConfiguration(locations="/com/townscript/forum/main-bean.xml")
@RestController
@RequestMapping(value="/comment")
public class CommentRESTController {
	
	public CommentRESTController() {
		super();
        if (commentService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/forum/main-bean.xml");
                commentService = (CommentHibernateService) context
                                .getBean("CommentHibernateServiceImpl");
        }
	}
	private CommentHibernateService commentService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/newComment", method=RequestMethod.POST)
	public ResponseEntity<HttpResponseVo> addComment(String commentJsonStr)
	{		
		TopicVo topicVo = null;
		CommentVo commentVo = null;
		UserVo userVo = null;
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			topicVo = mapper.readValue(commentJsonStr, TopicVo.class);
			commentVo = mapper.readValue(commentJsonStr, CommentVo.class);
			userVo = mapper.readValue(commentJsonStr, UserVo.class);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		CommentHibernate comment = new CommentHibernate();
		comment.setCommentId(commentVo.getCommentId());
		comment.setCommentDateTime(commentVo.getCommentDateTime());
		comment.setCommentType(commentVo.getCommentType());
		comment.setCommentValue(commentVo.getCommentValue());
		
		TopicHibernate topic = new TopicHibernate();
		topic.setTopicDateTime(topicVo.getTopicDateTime());
		topic.setTopicDescription(topicVo.getTopicDescription());
		topic.setTopicId(topicVo.getTopicId());
		topic.setTopicTags(topicVo.getTopicTags());
		topic.setTopicTitle(topicVo.getTopicTitle());
		topic.setTopicUrl(topicVo.getTopicUrl());
		
		UserHibernate user = new UserHibernate();
		user.setUserDateTime(userVo.getUserDateTime());
		user.setUserEmail(userVo.getUserEmail());
		user.setUserId(userVo.getUserId());
		user.setUserMobile(userVo.getUserMobile());
		user.setUserName(userVo.getUserName());
		user.setUserPropic(userVo.getUserPropic());
		
		long commentId = commentService.addComment(topic, comment, user);
		comment.setCommentId(commentId);
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.MSG_SUCCESS, comment, commentId), null, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/submitVote", method=RequestMethod.POST)
	public String submitVote(String voteJsonStr)
	{
		TopicVo topicVo = null;
		UserVo userVo = null;
		VoteVo voteVo = null;
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			topicVo = mapper.readValue(voteJsonStr, TopicVo.class);
			userVo = mapper.readValue(voteJsonStr, UserVo.class);
			voteVo = mapper.readValue(voteJsonStr, VoteVo.class);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ErrorCodes.FAIL;
		}
		
		TopicHibernate topic = new TopicHibernate();
		topic.setTopicDateTime(topicVo.getTopicDateTime());
		topic.setTopicDescription(topicVo.getTopicDescription());
		topic.setTopicId(topicVo.getTopicId());
		topic.setTopicTags(topicVo.getTopicTags());
		topic.setTopicTitle(topicVo.getTopicTitle());
		topic.setTopicUrl(topicVo.getTopicUrl());
		
		UserHibernate user = new UserHibernate();
		user.setUserDateTime(userVo.getUserDateTime());
		user.setUserEmail(userVo.getUserEmail());
		user.setUserId(userVo.getUserId());
		user.setUserMobile(userVo.getUserMobile());
		user.setUserName(userVo.getUserName());
		user.setUserPropic(userVo.getUserPropic());
		
		boolean submitVoteFlag = commentService.submitVote(topic, user, voteVo.getVoteValue());
		
		return ErrorCodes.SUCCESS;
	}
	
}
