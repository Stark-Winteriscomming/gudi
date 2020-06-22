package com.spring.user.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.vo.CodeVo;
import com.spring.user.dao.UserDao;
import com.spring.user.vo.UserVo;

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

	@Override
	public int registerUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace + "registerUser", userVo);
	}

	@Override
	public int checkDuplicatedId(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + "checkDuplicatedId", id);
	}

	@Override
	public int login(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + "login", userVo);
	}

	@Override
	public UserVo getUser(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + "getUser", user_id);
	}
	
}
