<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/board/boardWrite.js"></script>
<body>
	<form class="boardWrite" onsubmit="return false">
		<table align="center">
			<tr>
				<td align="right">
					<a href="/board/list">List</a>
				</td>
			</tr>
			<tr>
				<td>
					<button class="btn_plus">plus</button>
				</td>
			</tr>
			<tr>
				<td>
					<button class="btn_rm">rm</button>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input id="submit" type="button" value="작성">
				</td>
			</tr>
			<tr>
				<td>
					<table id="tbl_board_write" border="1" data-len="1">
						<tbody class="tb-board">
							<tr>
								<td width="120" align="center">Type</td>
								<td width="400">
									<select class="sel-board-type" name="list[0].boardType">
<!-- 										<option id="opt_default" value="opt_default" selected="selected">선택</option> -->
									</select>
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Title</td>
								<td width="400">
									<input name="list[0].boardTitle" type="text" size="50" value="${board.boardTitle}">
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Comment</td>
								<td width="400" valign="top">
									<!-- 								rows="20" cols="55" -->
									<textarea name="list[0].boardComment">${board.boardComment}</textarea>
								</td>
							</tr>
						</tbody>
						<tbody class="tb-board">
							<tr>
								<td>
									<input type="checkbox" class="cbox-board" data-index="1">
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Type</td>
								<td width="400">
									<select name="list[1].boardType" class="sel-board-type">
<!-- 										<option id="opt_default" value="opt_default" selected="selected">선택</option> -->
									</select>
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Title</td>
								<td width="400">
									<input name="list[1].boardTitle" type="text" size="50" value="${board.boardTitle}">
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Comment</td>
								<td width="400" valign="top">
									<!-- 								rows="20" cols="55" -->
									<textarea name="list[1].boardComment">${board.boardComment}</textarea>
								</td>
							</tr>
						</tbody>
						<tbody class="tb-board">
							<tr>
								<td>
									<input type="checkbox" class="cbox-board" data-index="2">
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Type</td>
								<td width="400">
									<select name="list[2].boardType" class="sel-board-type">
<!-- 										<option id="opt_default" value="opt_default" selected="selected">선택</option> -->
									</select>
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Title</td>
								<td width="400">
									<input name="list[2].boardTitle" type="text" size="50" value="${board.boardTitle}">
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Comment</td>
								<td width="400" valign="top">
									<!-- 								rows="20" cols="55" -->
									<textarea name="list[2].boardComment">${board.boardComment}</textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<table align="center">
						<tr>
							<td>Writer</td>
							<td>${userVo.user_name}</td>
						</tr>
					</table>
	</form>
</body>
</html>