package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.CodeVo;

public interface UserDao {

	List<CodeVo> getUserPhoneType(String codeType);
	
}
