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

	@RequestMapping(value = "/excelDownload")
	public void selectExcelList(HttpServletResponse response) throws Exception {
		boardService.selectExcelList(response);
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
		System.out.println("abc");
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
