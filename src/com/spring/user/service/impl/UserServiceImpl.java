package com.spring.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.vo.CodeVo;
import com.spring.user.dao.UserDao;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<CodeVo> getUserPhoneType(String codeType) {
		// TODO Auto-generated method stub
		return userDao.getUserPhoneType(codeType);
	}

	@Override
	public int registerUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.registerUser(userVo);
	}

	@Override
	public int checkDuplicatedId(String id) {
		// TODO Auto-generated method stub
		return userDao.checkDuplicatedId(id);
	}

	@Override
	public int login(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.login(userVo);
	}

}
