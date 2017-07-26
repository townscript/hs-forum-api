package com.townscript.forum.controller.comment;

import java.util.Date;

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
import com.townscript.forum.service.user.UserHibernateService;
import com.townscript.forum.vo.AddCommentVo;
import com.townscript.forum.vo.HttpResponseVo;
import com.townscript.forum.vo.SubmitVoteVo;

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
        if (userService == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                            "com/townscript/forum/main-bean.xml");
            userService = (UserHibernateService) context
                            .getBean("UserHibernateServiceImpl");
        }
	}
	private CommentHibernateService commentService;
	private UserHibernateService userService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/newComment", method=RequestMethod.POST)
	public ResponseEntity<HttpResponseVo> addComment(String commentJsonStr)
	{		
		AddCommentVo commentVo = null;
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			commentVo = mapper.readValue(commentJsonStr, AddCommentVo.class);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		CommentHibernate comment = new CommentHibernate();
		Date commentDateTime = new Date();
		comment.setCommentDateTime(commentDateTime);
		comment.setCommentType('T');
		comment.setCommentValue(commentVo.getCommentValue());
		
		TopicHibernate topic = new TopicHibernate();
		topic.setTopicId(commentVo.getTopicId());
		
		UserHibernate user = new UserHibernate();
		user.setUserId(userService.getUserIdByUserName(commentVo.getUserName()));
		
		long commentId = commentService.addComment(topic,comment,user);
		comment.setCommentId(commentId);
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.MSG_SUCCESS, comment, commentId), null, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/submitVote", method=RequestMethod.POST)
	public String submitVote(String voteJsonStr)
	{
		SubmitVoteVo submitVoteVo = null;
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			submitVoteVo = mapper.readValue(voteJsonStr, SubmitVoteVo.class);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ErrorCodes.FAIL;
		}
		
		TopicHibernate topic = new TopicHibernate();
		topic.setTopicId(submitVoteVo.getTopicId());
		
		UserHibernate user = new UserHibernate();
		user.setUserId(userService.getUserIdByUserName(submitVoteVo.getUserName()));
		
		boolean submitVoteFlag = commentService.submitVote(topic, user, submitVoteVo.getVoteValue());
		
		return ErrorCodes.SUCCESS;
	}
	
}
