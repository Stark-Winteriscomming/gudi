package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.Options;
import com.spring.board.vo.PageVo;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo, String options[], Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(pageVo, options, cri);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt();
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(boardVo);
	}

	@Override
	public int boardDelete(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return boardDao.boardDelete(boardVo);
	}

	@Override
	public int boardModify(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return boardDao.boardModify(boardVo);
	}

	@Override
	public List<CodeVo> selectBoardType(String codeType) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardType(codeType);
	}
	
}
