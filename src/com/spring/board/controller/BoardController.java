package com.spring.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.Criteria;
import com.spring.board.vo.Options;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.PagingVo;
import com.spring.common.CommonUtil;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// select menu
	@RequestMapping(value = "/selectBoardType/{codeType}", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
	@ResponseBody
	public String selectBoardTypeAction(@PathVariable("codeType")String codeType) throws Exception {

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

		result.put("success", (resultCnt > 0) ? "Y" : "N");
		result.put("boardType", boardVo.getBoardType());
		result.put("boardNum", String.valueOf(boardVo.getBoardNum()));
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		return callbackMsg;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(HttpServletRequest req, Locale locale, Model model, PageVo pageVo, Criteria cri) throws Exception {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		int page = 1;
		int totalCnt = 0;

		if (pageVo.getPageNo() == 0) {
			pageVo.setPageNo(page);
		}
		
		String options[] = req.getParameterValues("option");
		Options os = null;
		if(cri == null) cri = new Criteria();
		boardList = boardService.SelectBoardList(pageVo, options, cri);
		totalCnt = boardService.selectBoardCnt();

		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("pgvo", new PagingVo(totalCnt,cri));
		return "board/boardList";
	}

	@RequestMapping(value = "/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model, @PathVariable("boardType") String boardType,
			@PathVariable("boardNum") int boardNum) throws Exception {

		BoardVo boardVo = new BoardVo();

		boardVo = boardService.selectBoard(boardType, boardNum);

		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);

		return "board/boardView";
	}

	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception {

		return "board/boardWrite";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale, BoardVo boardVo) throws Exception {

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		result.put("success", (boardService.boardInsert(boardVo) > 0) ? "Y" : "N");
		result.put("href", "/board/list");
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);

		return callbackMsg;
	}

}
