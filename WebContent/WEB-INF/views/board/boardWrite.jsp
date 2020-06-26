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
				<td align="right">
					<input id="submit" type="button" value="작성">
				</td>
			</tr>
			<tr>
				<td>
					<table id="tbl_board_write" border="1">
						<tbody id="tb_board_write">
							<tr class="tr_btn_esc">
								<td colspan="2" align="right" style="border: 0">
									<button class="btn_rm">X</button>
								<td>
							</tr>
							<tr>
								<td width="120" align="center">Type</td>
								<td width="400">
									<select name="boardType" id="sel_board_type">
										<option id="opt_default" value="선택" selected="selected">선택</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Title</td>
								<td width="400">
									<input name="boardTitle" type="text" size="50" value="${board.boardTitle}">
								</td>
							</tr>
							<tr>
								<td width="120" align="center">Comment</td>
								<td width="400" valign="top">
<!-- 								rows="20" cols="55" -->
									<textarea name="boardComment">${board.boardComment}</textarea>
								</td>
							</tr>
							<div class="div_plus_btn">
								<tr class="tr_btn_plus">
									<td colspan="2" align="center">
										<button class="btn_plus">plus</button>
									</td>
								</tr>
							</div>
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
<script>
	var dupe_content = document.getElementById("tbl_board_write").innerHTML;
</script>
</html>