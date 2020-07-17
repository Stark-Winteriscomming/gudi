package com.spring.board.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.DateHeader;
import com.spring.board.vo.DayHeader;
import com.spring.board.vo.PageVo;
import com.sun.istack.internal.logging.Logger;

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
	public void selectExcelList(HttpServletResponse response) throws Exception {// 게시판 목록조회
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
	
	public void datePrintPerRow(int refRowNum, Sheet wSheet, Sheet rSheet, int i, int daySize, int start, int end, DataFormatter dataFormatter, Workbook wwb) {
		Row cDateRow = wSheet.createRow(i);
		Row refDateRow = rSheet.getRow(refRowNum);
		for(int k=0; k<=2; k++) {
			Cell refDateCell = refDateRow.getCell(k);
			for(int j=k; j < daySize * (end - start + 1); j=j+3) {
				Cell wCell = cDateRow.createCell(j);
				wCell.setCellValue(dataFormatter.formatCellValue(refDateCell));
				CellStyle wcs = wwb.createCellStyle();
				wcs.cloneStyleFrom(refDateCell.getCellStyle());
				wCell.setCellStyle(wcs);
			}
		}
	}
	@Override
	public void calendarDownload(HttpServletResponse response, int selectedYear, int selectedMonth) throws IOException, InvalidFormatException {
		String SAMPLE_XLSX_FILE_PATH = "C:\\_spring\\workspace\\springBoard\\WebContent\\WEB-INF\\test.xls";
		String[] arrDays = {"일","월","화","수","목","금","토"};
		// 월별일수 (평년 기준)
		int[] arrDatesPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] arrCalculatedDatesNum = new int[35];
		
		int index = 0;
		//7월
		selectedMonth = 6;
		
		// 7월 1일이 수요일이라 가정.
		int firstDay = 3;
		int preMonthEnd =  arrDatesPerMonth[selectedMonth - 1];
		int preMonthStart = arrDatesPerMonth[selectedMonth - 1] - firstDay - 1;
		
		int preMonthDatesSize = preMonthEnd - preMonthStart + 1;
		int nextMonthStart = preMonthDatesSize + arrDatesPerMonth[selectedMonth - 1];
		int preMonthIndex = 0;
		for(int i=preMonthStart; i<=preMonthEnd; i++) {
			arrCalculatedDatesNum[preMonthIndex++] = i;
		}
		int nextMonthIndex = 1;
		for(int i=nextMonthStart; i<arrCalculatedDatesNum.length; i++) {
			arrCalculatedDatesNum[i] = nextMonthIndex++;
		}
		
		
		DataFormatter dataFormatter = new DataFormatter();

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook rwb = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		Sheet rSheet = rwb.getSheetAt(0);
		
		String strDayHeader = dataFormatter.formatCellValue(rwb.getSheetAt(0).getRow(0).getCell(0));
		String strDateHeader = dataFormatter.formatCellValue(rwb.getSheetAt(0).getRow(1).getCell(0));
		ObjectMapper mapper = new ObjectMapper(); 
		DayHeader dayHeader = mapper.readValue(strDayHeader, DayHeader.class);
		DateHeader dateHeader = mapper.readValue(strDateHeader, DateHeader.class);
		int locRow = 2;
		int daySize = arrDays.length;
		
		//day
		int row = Integer.parseInt(dayHeader.getRow());
		int start = Integer.parseInt(dayHeader.getStart());
		int end = Integer.parseInt(dayHeader.getEnd());
		
		//date
		int rStart = Integer.parseInt(dateHeader.getrStart());
		int rEnd = Integer.parseInt(dateHeader.getrEnd());
		int cStart = Integer.parseInt(dateHeader.getcStart());
		int cEnd = Integer.parseInt(dateHeader.getcEnd());
		int dRow = Integer.parseInt(dateHeader.getdRow());
		int dCol = Integer.parseInt(dateHeader.getdCol());
		
		Workbook wwb = new HSSFWorkbook();
		Sheet wSheet = wwb.createSheet();
		Row wRow = wSheet.createRow(locRow);
		
//		day printing
		Row refRow = rSheet.getRow(row);
		for(int i = start; i <= end; i++) {
			Cell refCell = refRow.getCell(i);
			for(int j=i; j < daySize * (end - start + 1); j=j+3) {
				Cell wCell = wRow.createCell(j);
				if(j % 3 == 1) {
					wCell.setCellValue(arrDays[index++]);
				}
				else wCell.setCellValue(dataFormatter.formatCellValue(refCell));
				
				CellStyle wcs = wwb.createCellStyle();
				wcs.cloneStyleFrom(refCell.getCellStyle());
				wCell.setCellStyle(wcs);
			}
		}
//		date priting
		for(int i=3; i<=17; i++) {
			if(i % 3 == 0) {datePrintPerRow(3, wSheet, rSheet, i, daySize, cStart, cEnd, dataFormatter, wwb);}
			if(i % 3 == 1) {datePrintPerRow(4, wSheet, rSheet, i, daySize, cStart, cEnd, dataFormatter, wwb);}
			if(i % 3 == 2) {datePrintPerRow(5, wSheet, rSheet, i, daySize, cStart, cEnd, dataFormatter, wwb);}
		}
			
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xls\""));
		// 엑셀 출력
		wwb.write(response.getOutputStream());
		// Closing the workbook
		wwb.close();
		rwb.close();
	}

}
