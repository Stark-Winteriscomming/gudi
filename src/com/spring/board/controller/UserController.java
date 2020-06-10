package com.spring.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.service.UserService;
import com.spring.board.vo.CodeVo;
import com.spring.common.CommonUtil;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService; 
	
	@ResponseBody
	@RequestMapping(value = "/user/phone/type", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	public String getUserPhoneType() throws Exception {
		String codeType = "phone"; 
		List<CodeVo> codeList = userService.getUserPhoneType(codeType); 
		
		String callbackMsg = CommonUtil.getJsonCallBackString(" ", codeList);
		return callbackMsg;
	}
	
	@RequestMapping(value = "/user/join.do", method = RequestMethod.GET)
	public String userJoin() throws Exception {
		
		return "user/join";
	}
}
