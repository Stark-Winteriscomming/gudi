package com.spring.user.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.spring.user.service.CustomDeamon;
import com.spring.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private CustomDeamon CustomDeamon; 
	
	@ResponseBody
	@RequestMapping(value = "/selectPhoneType/{codeType}", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	public String getUserPhoneType(@PathVariable("codeType")String codeType) throws Exception {
		List<CodeVo> codeList = userService.getUserPhoneType(codeType); 
		
		String callbackMsg = CommonUtil.getJsonCallBackString(" ", codeList);
		return callbackMsg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/check/dupe/{id}", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	public String checkDuplicatedId(@PathVariable("id") String id) throws Exception {
		
		String leftTime="";
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		if(!CustomDeamon.isExist(id)) CustomDeamon.add(id);
		else {
			for(int i=0; i<CustomDeamon.lockedIdList.size(); i++) {
				Iterator iterator = CustomDeamon.lockedIdList.get(i).keySet().iterator();
				while(iterator.hasNext()) {
					String key = (String)iterator.next();
					if(key.equals(id)) {
						leftTime = (String)(CustomDeamon.lockedIdList.get(i).get(key));
					}    
				}
			}
		}
		
		result.put("leftTime", leftTime); 
		CustomDeamon.initFlag = 1; 		
		
		
		return CommonUtil.getJsonCallBackString(" ", userService.checkDuplicatedId(id));
	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoin() throws Exception {
		
		
		return "user/join";
	}
}
