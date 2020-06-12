<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/board/boardView.js"></script>
</head>
<body>
	<table align="center">
		<tr>
			<td>
				<form id="frm_input">
					<input type="hidden" name="boardType" value="${boardType }">
					<input type="hidden" name="boardNum" value="${boardNum }">
					<table border="1">
						<tr>
							<td width="120" align="center">Title</td>
							<td width="400">
								<div>
									<input type="hidden" id="i_board_boardTitle"
										class="board-item" name="boardTitle"
										placeholder="${board.boardTitle}">
								</div>
								<div id="detail_boardTitle">${board.boardTitle}</div>
							</td>
						</tr>
						<tr>
							<td height="300" align="center">Comment</td>
							<td>
								<div>
									<input type="hidden" id="i_board_boardComment"
										class="board-item" name="boardComment"
										placeholder="${board.boardComment}">
								</div>
								<div id="detail_boardComment">${board.boardComment}</div>
							</td>
						</tr>
						<tr>
							<td align="center">Writer</td>
							<td></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr><td><button id="btn_remove">remove</button></td></tr>
		<tr><td><button id="btn_modify">modify</button></td></tr>
		<tr><td><button id="btn_modify_complete" style="display : none">작성완료</button></td></tr>
		<tr>
			<td align="right"><a href="/board/boardList.do">List</a></td>
		</tr>
	</table>
	<form id="frm_remove">
		<input type="hidden" name="boardType" value="${boardType}"> <input
			type="hidden" name="boardNum" value="${boardNum}">
	</form>
</body>
</html>