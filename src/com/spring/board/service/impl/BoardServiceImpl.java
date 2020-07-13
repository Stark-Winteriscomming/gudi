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

	@Override
	public void calendarDownload(HttpServletResponse response) throws IOException, InvalidFormatException {
		// TODO Auto-generated method stub
		String SAMPLE_XLSX_FILE_PATH = "C:\\_spring\\workspace\\springBoard\\WebContent\\WEB-INF\\test.xls";

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		Workbook wb = new HSSFWorkbook();
		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		/*
		 * ============================================================= Iterating over
		 * all the sheets in the workbook (Multiple ways)
		 * =============================================================
		 */

		// 2. Or you can use a for-each loop
		System.out.println("Retrieving Sheets using for-each loop");
		for (Sheet sheet : workbook) {
			System.out.println("=> " + sheet.getSheetName());
		}

		/*
		 * ================================================================== Iterating
		 * over all the rows and columns in a Sheet (Multiple ways)
		 * ==================================================================
		 */

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		Sheet cSheet = wb.createSheet("게시판");
		Row cRow = null;
		int rowNo = 0;
		Cell cCell = null;

		//
		List<CellRangeAddress> regionsList = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			regionsList.add(sheet.getMergedRegion(i));
		}
		//
		System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
		for (Row row : sheet) {
			cRow = cSheet.createRow(rowNo++);
			short n = row.getLastCellNum();
			int i = 0;
			for (int j = 0; j < 7; j++) {
				for (i = j * 3; i < j * 3 + n; i++) {
					int index = i % 3;
					cCell = cRow.createCell(i);
					cCell.setCellValue(dataFormatter.formatCellValue(row.getCell(index)));
					CellStyle newStyle = wb.createCellStyle();
					newStyle.cloneStyleFrom(row.getCell(index).getCellStyle());
					cCell.setCellStyle(newStyle);
				}
			}

			System.out.println();
		}
		for (CellRangeAddress address : regionsList) {
			int firstRow = address.getFirstRow();
			int lastRow = address.getLastRow();
			int firstColumn = address.getFirstColumn();
			int lastColumn = address.getLastColumn();
			
//				cSheet.addMergedRegion(new CellRangeAddress(address.getFirstRow(), address.getLastRow(),
//						address.getFirstColumn(), address.getLastColumn()));
			
			for (int i = 0; i < 21; i++) {
				if(i % 3 == 0) {
					cSheet.addMergedRegion(new CellRangeAddress(1, 1,
							i, i+2));
				}
			}
			
		}

		response.setHeader("Set-Cookie", "fileDownload=true; path=/");

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xls\""));

		// 엑셀 출력
		wb.write(response.getOutputStream());

		// Closing the workbook
		workbook.close();
	}

}
