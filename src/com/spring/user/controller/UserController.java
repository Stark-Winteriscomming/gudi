package com.spring.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.spring.user.service.impl.AsyncTaskService;
import com.spring.user.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="asyncTaskService")
	private AsyncTaskService asyncTaskService;
	
	@Autowired
	private UserService userService;
	
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
//		if(asyncTaskService.initFlag == 0) {
//			asyncTaskService.run();
//			asyncTaskService.initFlag = 1;
//		}
//			
//		if(!asyncTaskService.isExist(id)) {
//			System.out.println(">>> adding...");
//			asyncTaskService.add(id);
//		} 
//		else {
//			System.out.println("size: " + asyncTaskService.lockedIdList.size());
//			for(int i=0; i<asyncTaskService.lockedIdList.size(); i++) {
//				Iterator iterator = asyncTaskService.lockedIdList.get(i).keySet().iterator();
//				while(iterator.hasNext()) {
//					String key = (String)iterator.next();
//					if(key.equals(id)) {
//						leftTime = (String)(asyncTaskService.lockedIdList.get(i).get(key));
//					}    
//				}
//			}
//		}
		
		result.put("leftTime", leftTime); 
		
		String msg = ((userService.checkDuplicatedId(id)) == 0) ? "ndupe" : "dupe";
		result.put("msg", msg);
//		return CommonUtil.getJsonCallBackString(" ", userService.checkDuplicatedId(id));
		return CommonUtil.getJsonCallBackString(" ", result);
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoinForm() throws Exception {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String userJoin(UserVo userVo) throws Exception {
		return ((userService.registerUser(userVo) == 1 ? "/user/joinSuccess" : "/user/joinFail"));  
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLoginForm() throws Exception {
		return "/user/login";  
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(HttpServletRequest req, UserVo userVo) throws Exception {
		HashMap<String, String> data = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int result = userService.login(userVo);
		if(result == 1) {
			HttpSession session = req.getSession();
			String user_id = userVo.getUser_id();
			session.setAttribute("userVo", (UserVo)userService.getUser(user_id));
			data.put("msg", "Y");
		}else  data.put("msg", "N");
		String idExistMsg = ((userService.checkDuplicatedId(userVo.getUser_id())) == 0) ? "none"  : "exist";
		data.put("idExistMsg", idExistMsg);
		return CommonUtil.getJsonCallBackString(" ", data);	
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String userLogout(HttpServletRequest req) throws Exception {
		req.getSession().invalidate();
		return "redirect:/board/list";
	}
}

