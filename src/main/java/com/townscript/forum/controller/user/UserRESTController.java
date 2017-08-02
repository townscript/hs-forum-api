package com.townscript.forum.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.townscript.forum.constants.Constants;
import com.townscript.forum.model.user.UserHibernate;
import com.townscript.forum.service.LoginService;
import com.townscript.forum.service.user.UserHibernateService;
import com.townscript.forum.vo.AddUserVo;

@RestController
@RequestMapping(value="/user")
public class UserRESTController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserHibernateService userService;
	private LoginService loginService;
	public UserRESTController() {
		super();
        if (userService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/forum/main-bean.xml");
                userService = (UserHibernateService) context
                                .getBean("UserHibernateServiceImpl");
        }
        if (loginService == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                            "com/townscript/forum/main-bean.xml");
            loginService = (LoginService) context
                            .getBean("LoginServiceImpl");
    }
	}
	

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public UserHibernate getUserByUserNameAndPassword(@RequestParam(value="userName") String userName, @RequestParam(value="userPassword") String userPassword){
		if(loginService.checkLogin(userName, userPassword))
		{
			UserHibernate user = userService.getUserByUserNameAndPassword(userName, userPassword);
			request.getSession().setAttribute("userId",user.getUserId());
			request.getSession().setAttribute("userName",user.getUserName());
			return user;
		}
		return null;
		
	}
	
	@RequestMapping(value ="/checkSession",method = RequestMethod.GET)
	public long checkSession(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Inside checksession controller");
		//HttpSession session = request.getSession();
		if(request.getSession().getAttribute("userId")!=null){
			System.out.println(request.getSession().getAttribute("userId"));
			System.out.println("returning true in checksession");
			return (long)request.getSession().getAttribute("Id");
		}
		System.out.println("returning false in checksession");
		return Constants.DEF_ID;
	}
	
	@RequestMapping(value ="/logout",method = RequestMethod.GET)
	public void logoutUser(HttpServletRequest request, HttpServletResponse response){
			 System.out.println("Inside logout controller");
			 //HttpSession session=request.getSession();  
			 request.getSession().invalidate();
		     
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/newUser", method=RequestMethod.POST) 
	public UserHibernate insertUser(@RequestParam(value="dataJson") String userJsonStr) {
		AddUserVo addUserVo = null;
		UserHibernate user = null;
		
		try {
			/*Gson gson = new GsonBuilder().create();
			addUserVo = gson.fromJson(userJsonStr, AddUserVo.class);*/
			ObjectMapper mapper = new ObjectMapper();
			addUserVo = mapper.readValue(userJsonStr, AddUserVo.class);
			
		} catch(Exception e) {
			e.printStackTrace();
			//return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		user.setPassword(addUserVo.getUserPassword());
		user.setUserEmail(addUserVo.getUserEmail());
		user.setUserMobile(addUserVo.getUserMobile());
		user.setUserName(addUserVo.getUserName());
		user.setUserPropic(addUserVo.getUserPropic());
		//Date userDateTime = new Date();
		user.setUserDateTime(null);

		if(userService.insertUser(user)) {
			return user;
			//return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, user, null), null, HttpStatus.BAD_REQUEST);
		}
		
		return user;
		//return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
	}
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value="/isUniqueUser", method=RequestMethod.POST)
	public boolean isUniqueUser(@RequestParam(value="userName") String userName){
		boolean isUnique = false; 
		
		try{
			isUnique = userService.isUniqueUser(userName);
			return isUnique;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isUnique;
	}
	
}
