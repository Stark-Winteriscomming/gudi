package com.spring.user.dao;

import java.util.List;

import com.spring.board.vo.CodeVo;
import com.spring.user.vo.UserVo;

public interface UserDao {
	public List<CodeVo> getUserPhoneType(String codeType);
	int registerUser(UserVo userVo);
	public int checkDuplicatedId(String id);
	public int login(UserVo userVo);
}
