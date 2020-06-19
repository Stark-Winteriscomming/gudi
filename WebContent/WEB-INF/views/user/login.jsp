<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/user/login.js"></script>
</head>
<body>
	<form id="frm_login" method="post">
		<table align="center">
			<tr>
				<td>
					<table border="1">
						<tr>
							<td width="40" align="center">id</td>
							<td width="40"><input name="user_id" type="text" size="50"></td>
						</tr>
						<tr>
							<td width="40" align="center">password</td>
							<td width="40">
								<input name="user_pw" type="password" size="50">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right">
					<button id="btn_login">로그인</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>