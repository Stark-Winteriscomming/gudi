package com.spring.board.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.Options;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.PagingVo;
import com.spring.common.CommonUtil;
import com.spring.user.vo.UserVo;


@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	SqlSession sqlSession;

	@RequestMapping(value = "/excelDownload")
	public void selectExcelList(HttpServletResponse response) {

		// 메모리에 100개의 행을 유지합니다. 행의 수가 넘으면 디스크에 적습니다.
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		Sheet sheet = wb.createSheet();

		try {
			sqlSession.select("selectExcelList", "게시물", new ResultHandler<BoardVo>() {

				@Override
				public void handleResult(ResultContext<? extends BoardVo> context) {
					// TODO Auto-generated method stub
					BoardVo vo = context.getResultObject(); 
					Row row = sheet.createRow(context.getResultCount() - 1); 
					Cell cell = null; 
					cell = row.createCell(0); 
					cell.setCellValue(vo.getBoardTitle().toString()); cell = row.createCell(1); 
					cell.setCellValue(vo.getBoardComment()); 
					cell = row.createCell(2); 
				}
				
			});

			response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
			wb.write(response.getOutputStream());

		} catch (Exception e) {

			response.setHeader("Set-Cookie", "fileDownload=false; path=/");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Content-Type", "text/html; charset=utf-8");

			OutputStream out = null;
			try {
				out = response.getOutputStream();
				byte[] data = new String("fail..").getBytes();
				out.write(data, 0, data.length);
			} catch (Exception ignore) {
				ignore.printStackTrace();
			} finally {
				if (out != null)
					try {
						out.close();
					} catch (Exception ignore) {
					}
			}

		} finally {
//			sqlSession.close();

			// 디스크 적었던 임시파일을 제거합니다.
			wb.dispose();
			try {
				wb.close();
			} catch (Exception ignore) {
			}
		}
	}

	// select menu
	@RequestMapping(value = "/selectBoardType/{codeType}", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	@ResponseBody
	public String selectBoardTypeAction(@PathVariable("codeType") String codeType) throws Exception {

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		List<CodeVo> codeList = boardService.selectBoardType(codeType);

		String callbackMsg = commonUtil.getJsonCallBackString(" ", codeList);
		return callbackMsg;
	}

	// remove
	@RequestMapping(value = "/boardRemoveAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardRemoveAction(BoardVo boardVo) throws Exception {

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		int resultCnt = boardService.boardDelete(boardVo);

		result.put("success", (resultCnt > 0) ? "Y" : "N");
		result.put("href", "/board/list");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		return callbackMsg;
	}

	// modify
	@RequestMapping(value = "/boardModifyAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardModifyAction(BoardVo boardVo) throws Exception {

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		int resultCnt = boardService.boardModify(boardVo);
		String boardType = boardVo.getBoardType();
		int boardNum = boardVo.getBoardNum();
		result.put("success", (resultCnt > 0) ? "Y" : "N");
		result.put("boardType", boardType);
		result.put("boardNum", String.valueOf(boardNum));
		result.put("href", "/board/" + boardType + "/" + boardNum + "/" + "boardView.do");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		return callbackMsg;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(HttpServletRequest req, Locale locale, Model model, PageVo pageVo, Criteria cri)
			throws Exception {
		String str = "bbb";
		System.out.println(str);
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		int page = 1;
		int totalCnt = 0;

		if (pageVo.getPageNo() == 0) {
			pageVo.setPageNo(page);
		}

		String options[] = req.getParameterValues("option");
		Options os = null;
		if (cri == null)
			cri = new Criteria();
		boardList = boardService.SelectBoardList(pageVo, options, cri);
		totalCnt = boardService.selectBoardCnt();

		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("pgvo", new PagingVo(totalCnt, cri));
		return "board/boardList";
	}

	@RequestMapping(value = "/{boardType}/{boardNum}/{user_id}/boardView.do", method = RequestMethod.GET)
	public String boardView(HttpServletRequest req, Locale locale, Model model,
			@PathVariable("boardType") String boardType, @PathVariable("boardNum") int boardNum,
			@PathVariable("user_id") String user_id) throws Exception {

		BoardVo boardVo = boardService.selectBoard(boardType, boardNum, user_id);

		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);

		return "board/boardView";
	}

	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(HttpServletRequest req, Locale locale, Model model) throws Exception {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("userVo");
		if (obj == null)
			return "user/login";
		return "board/boardWrite";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(HttpServletRequest req, Locale locale, BoardVo boardVo,
			@RequestParam Map<String, Object> parameters) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("userVo");
		UserVo uvo = (UserVo) obj;
		for (BoardVo bvo : boardVo.getList()) {
			bvo.setCreator(uvo.getUser_id());
		}
		//
		boardService.boardInsert(boardVo.getList());
		result.put("success", "Y");
		result.put("href", "/board/list");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);

		return callbackMsg;
	}

}
