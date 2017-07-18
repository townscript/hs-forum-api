package com.townscript.forum.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.UserHibernateService;

//@ContextConfiguration(locations="/com/townscript/forum/main-bean.xml")
@RestController
@RequestMapping(value="/user")
public class UserRESTController {
	
	public UserRESTController() {
		super();
        if (userService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/forum/main-bean.xml");
                userService = (UserHibernateService) context
                                .getBean("UserHibernateServiceImpl");
        }
	}
	private UserHibernateService userService;
	
//	public UserHibernateService getUserService() {
//		return userService;
//	}
//
//	public void setUserService(UserHibernateService userService) {
//		this.userService = userService;
//	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public UserHibernate getUserByUserNameAndPassword(@RequestParam(value="userName") String userName, @RequestParam(value="userPassword") String userPassword){
		return userService.getUserByUserNameAndPassword(userName, userPassword);
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
