<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/board/boardList.js"></script>
<body>
	<table align="center">
		<tr><td>${userVo.user_name}</td></tr>
		<c:if test="${userVo == null}"><tr><td align="right"><a href="/user/join">회원가입</a></td></tr></c:if>
		<tr>
			<td align="right">total : ${totalCnt}</td>
		</tr>
		<tr>
			<td>
				<table id="boardTable" border="1">
					<tr>
						<td width="80" align="center">Type</td>
						<td width="40" align="center">No</td>
						<td width="300" align="center">Title</td>
					</tr>
					<c:forEach items="${boardList}" var="list">
						<tr>
							<td align="center">${list.codeVo.code_name}</td>
							<td>${list.boardNum}</td>
							<td>
								<a href="/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<c:if test="${userVo == null}">
					<a href="/user/login">로그인</a>
				</c:if>
				<c:if test="${userVo != null}">
					<a href="/user/logout">로그아웃</a>
				</c:if>
				<c:if test="${userVo != null}">
					<a href="/board/boardWrite.do">글쓰기</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" name="total" value="total" onClick="toggle(this)" /> <label for="total">전체</label>
				<form id=frm_search>
					<div id="search_box"></div>
					<input type="submit" value="조회" style="display: none">
				</form>
				<button id="btn_search">조회</button>
				<!-- 				<a id="a_search" href="">조회</a> -->
			</td>
		</tr>
		<tr>
			<td>
				<ul class="pagination">
					<c:if test="${pgvo.prev }">
						<li class="page-item"><a class="page-link" href="/board/list?pageNum=${pgvo.startPage-1 }&amount=${pgvo.cri.amount}">Prev</a></li>
					</c:if>
					<c:forEach var="i" begin="${pgvo.startPage }" end="${pgvo.endPage }">
						<li class="page-item ${pgvo.cri.pageNum == i ? 'active' : '' }"><a class="page-link" href="/board/list?pageNum=${i }&amount=${pgvo.cri.amount}">${i }</a></li>
					</c:forEach>
					<c:if test="${pgvo.next }">
						<li class="page-item"><a class="page-link" href="/board/list?pageNum=${pgvo.endPage+1 }&amount=${pgvo.cri.amount}">Next</a></li>
					</c:if>
				</ul>
			</td>
		</tr>
	</table>
</body>
</html>