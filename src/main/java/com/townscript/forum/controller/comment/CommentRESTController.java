package com.townscript.forum.controller.comment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.townscript.forum.model.comment.CommentHibernate;
import com.townscript.forum.model.comment.CommentMapHibernate;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.comment.CommentHibernateService;


//@ContextConfiguration(locations="/com/townscript/forum/main-bean.xml")
@RestController
@RequestMapping(value="/user")
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
	
//	public UserHibernateService getUserService() {
//		return userService;
//	}
//
//	public void setUserService(UserHibernateService userService) {
//		this.userService = userService;
//	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/newComment", method=RequestMethod.POST)
	public boolean addComment(@RequestParam(value="topicId") String topicId, @RequestParam(value="userName") String userName, @RequestParam(value="commentValue") String commentValue, @RequestParam(value="commentValue") String commentType){
		CommentHibernate comment = new CommentHibernate();
		CommentMapHibernate commentMap = new CommentMapHibernate();
		
		
		user.setUserEmail(userEmail);
		user.setPassword(userPassword);
		return userService.insertUser(user);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public boolean insertUser(@RequestParam(value="userName") String userName, @RequestParam(value="userEmail") String userEmail, @RequestParam(value="userPassword") String userPassword)
	{
		UserHibernate user = new UserHibernate();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setPassword(userPassword);
		return userService.insertUser(user);
	}
	
}
