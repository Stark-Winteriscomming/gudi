package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.UserDao;
import com.spring.board.service.UserService;
import com.spring.board.vo.CodeVo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<CodeVo> getUserPhoneType(String codeType) {
		// TODO Auto-generated method stub
		return userDao.getUserPhoneType(codeType);
	}

}
