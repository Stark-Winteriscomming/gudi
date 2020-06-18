package com.spring.user.service;

import java.util.List;

import com.spring.board.vo.CodeVo;
import com.spring.user.vo.UserVo;

public interface UserService {

	List<CodeVo> getUserPhoneType(String codeType);

	int registerUser(UserVo userVo);

	int checkDuplicatedId(String id);

	int login(UserVo userVo);
	
}
