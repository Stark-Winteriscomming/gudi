package com.spring.user.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.vo.CodeVo;
import com.spring.common.CommonUtil;
import com.spring.user.service.UserService;

import jdk.nashorn.internal.objects.annotations.Getter;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService; 
	
	@ResponseBody
	@RequestMapping(value = "/selectPhoneType", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	public String getUserPhoneType() throws Exception {
		String codeType = "phone"; 
		List<CodeVo> codeList = userService.getUserPhoneType(codeType); 
		
		String callbackMsg = CommonUtil.getJsonCallBackString(" ", codeList);
		return callbackMsg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/check/dupe/{id}", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	public String checkDuplicatedId(@PathVariable("id") String id) throws Exception {
		return CommonUtil.getJsonCallBackString(" ", userService.checkDuplicatedId(id));
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoin() throws Exception {
		
		return "user/join";
	}
}
