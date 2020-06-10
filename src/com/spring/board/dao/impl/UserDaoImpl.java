package com.spring.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.UserDao;
import com.spring.board.vo.CodeVo;

@Repository
public class UserDaoImpl implements UserDao{
	final static String namespace = "user."; 
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CodeVo> getUserPhoneType(String codeType) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +"getUserPhoneType", codeType);
	}
	
}
