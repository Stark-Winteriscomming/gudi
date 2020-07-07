package com.spring.board.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.PageVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	SqlSession sqlSession;

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
	public BoardVo selectBoard(String boardType, int boardNum, String user_id) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();

		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		boardVo.setCreator(user_id);

		return boardDao.selectBoard(boardVo);
	}

	@Override
	public void boardInsert(List<BoardVo> bList) throws Exception {
		// TODO Auto-generated method stub
		for (BoardVo bvo : bList) {
			boardDao.boardInsert(bvo);
		}
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

	@Transactional
	@Override
	public void selectExcelList(HttpServletResponse response) throws Exception  {// 게시판 목록조회
		List<BoardVo> list = sqlSession.selectList("board.excel");
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("게시판");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;

		row = sheet.createRow(rowNo++);

		cell = row.createCell(0);
		cell.setCellValue("타입");
		cell = row.createCell(1);
		cell.setCellValue("제목");
		cell = row.createCell(2);
		cell.setCellValue("코멘트");

		for (BoardVo vo : list) {

			row = sheet.createRow(rowNo++);

			cell = row.createCell(0);
			cell.setCellValue(vo.getBoardType());
			cell = row.createCell(1);
			cell.setCellValue(vo.getBoardTitle());
			cell = row.createCell(2);
			cell.setCellValue(vo.getBoardComment());

		}

		response.setHeader("Set-Cookie", "fileDownload=true; path=/");

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xls\""));

		// 엑셀 출력
		wb.write(response.getOutputStream());

		wb.close();
	}

}
