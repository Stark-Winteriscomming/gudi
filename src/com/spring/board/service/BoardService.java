package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.Options;
import com.spring.board.vo.PageVo;

public interface BoardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(PageVo pageVo, String[] options, Criteria cri) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt() throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;

	public int boardDelete(BoardVo boardVo);

	public int boardModify(BoardVo boardVo);

	public List<CodeVo> selectBoardType(String codeType);

}
