package com.townscript.forum.controller.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.user.UserHibernateService;
import com.townscript.forum.vo.AddUserVo;

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

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public UserHibernate getUserByUserNameAndPassword(@RequestParam(value="userName") String userName, @RequestParam(value="userPassword") String userPassword){
		return userService.getUserByUserNameAndPassword(userName, userPassword);
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/newUser", headers="*", method=RequestMethod.POST)
	//ResponseEntity<HttpResponseVo>
	//@RequestParam(value="data-json", required=true) 
	public boolean insertUser(String userJsonStr)
	{
		AddUserVo addUserVo = null;
		try{
			/*Gson gson = new GsonBuilder().create();
			addUserVo = gson.fromJson(userJsonStr, AddUserVo.class);*/
			ObjectMapper mapper = new ObjectMapper();
			addUserVo = mapper.readValue(userJsonStr, AddUserVo.class);
		}
		catch(Exception e) {
			e.printStackTrace();
			/*return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);*/
			return false;
		}
		
		UserHibernate user = new UserHibernate();
		user.setPassword(addUserVo.getUserPassword());
		user.setUserEmail(addUserVo.getUserEmail());
		user.setUserMobile(addUserVo.getUserMobile());
		user.setUserName(addUserVo.getUserName());
		user.setUserPropic(addUserVo.getUserPropic());
		/*Date userDateTime = new Date();
		user.setUserDateTime(userDateTime);*/
		
		/*return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, user, null), null, HttpStatus.BAD_REQUEST);*/
		return userService.insertUser(user);
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/isUniqueUser", method=RequestMethod.POST)
	public boolean isUniqueUser(@RequestParam(value="userName") String userName){
		boolean isUnique = false; 
		
		try{
			isUnique = userService.isUniqueUser(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isUnique;
	}
	
}
