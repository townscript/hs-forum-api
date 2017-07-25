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
import com.townscript.forum.vo.LoginVo;
import com.townscript.forum.vo.HttpResponseVo;

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
	
	@RequestMapping(value="/checkLogin1", method=RequestMethod.POST)
	public ResponseEntity<HttpResponseVo> checkLogin1(String loginJsonStr){
		LoginVo loginVo = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			loginVo = mapper.readValue(loginJsonStr, LoginVo.class);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		boolean isvalidLogin = false;
		try {
			isvalidLogin = loginService.checkLogin(loginVo.getUserName(), loginVo.getPassword());
			if (isvalidLogin) {
				return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.SUCCESS, Constants.LOGIN_SUCCESS, isvalidLogin, null), null, HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.FAIL, Constants.MSG_FAIL, null, null), null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<HttpResponseVo>(new HttpResponseVo(ErrorCodes.LOGIN_ERROR, Constants.LOGIN_ERROR, isvalidLogin, null), null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST)
	public String checkLogin(String username, String password){
		
		boolean isvalidLogin = false;
		try {
			isvalidLogin = loginService.checkLogin(username, password);
			if (isvalidLogin) {
			System.out.println("###############################################");
				return Constants.MSG_SUCCESS;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Constants.MSG_FAIL;
	}
	
	@RequestMapping(value="/loginSuccess", method=RequestMethod.POST)
	public String loginSuccess(){
		
		return null;
	}
}
