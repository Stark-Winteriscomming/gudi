package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.Options;
import com.spring.board.vo.PageVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(PageVo pageVo, String[] options, Criteria cri) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt() throws Exception;

	public int boardInsert(List<BoardVo> bList) throws Exception;

	public int boardDelete(BoardVo boardVo);

	public int boardModify(BoardVo boardVo);

	public List<CodeVo> selectBoardType(String codeType);

}
