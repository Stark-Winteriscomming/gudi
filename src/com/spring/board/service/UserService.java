package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.CodeVo;

public interface UserService {

	List<CodeVo> getUserPhoneType(String codeType);
	
}
