package com.townscript.forum.controller;

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
import com.townscript.forum.service.LoginService;
import com.townscript.forum.vo.HttpResponseVo;
import com.townscript.forum.vo.LoginVo;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	
	private LoginService loginService;
	
	public LoginController() {
		super();
        if (loginService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/forum/main-bean.xml");
                loginService = (LoginService) context
                                .getBean("LoginServiceImpl");
        }
	}
	
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST)
	public boolean checkLogin(@RequestParam(value="data-json") String loginJsonStr){
		LoginVo loginVo = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			loginVo = mapper.readValue(loginJsonStr, LoginVo.class);
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		boolean isvalidLogin = false;
		try {
			isvalidLogin = loginService.checkLogin(loginVo.getUserName(), loginVo.getPassword());
			if (isvalidLogin) {
				return isvalidLogin;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
}
