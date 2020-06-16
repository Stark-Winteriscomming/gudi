<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>join view</title>
<script type="text/javascript" src="/resources/js/message-jquery-validate.js"></script>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/user/join.js"></script>
</head>
<body>
	<form class="cmxform" id="frm_join" method="get">
		<fieldset>
			<table align="center">
				<tr>
					<td align="left">
						<a href="/board/list">List</a>
					</td>
				</tr>
				<tr>
					<!-- 			<td> -->
					<table border="1">
						<tr>
							<td width="120" align="center">id</td>
							<td width="300">
								<div>
									<input type="text" id="user_id" name="user_id" data-check="unchecked" required>
									<button class="no" id="btn_duplicate_check">중복확인</button>
									<label class="error" for="user_id"></label>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw</td>
							<td width="400">
								<span><input type="text" name="user_pw" minlength="6" maxlength="12" required></span>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw check</td>
							<td width="300">
								<div>
									<input type="text" name="user_pw_check" required>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">name</td>
							<td width="300">
								<div>
									<input type="text" id="user_name" name="user_name" oninput="maxLengthCheck(this, 4)">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">phone</td>
							<td width="300">
								<div>
									<select id="sel_user_phone1" name="user_phone1">
										<option>선택</option>
									</select>
									<input type="number" style="width: 60px" id="user_phone2" name="user_phone2" oninput="maxLengthCheck(this, 4)" /> <input type="number" style="width: 60px" id="user_phone3" name="user_phone3" oninput="maxLengthCheck(this, 4)" /> <span id="error_msg"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">postNo</td>
							<td width="300">
								<div>
									<input type="text" name="user_addr1">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">address</td>
							<td width="300">
								<div>
									<input type="text" name="user_addr2">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">company</td>
							<td width="500">
								<div>
									<input type="text" name="user_company">
								</div>
							</td>
						</tr>
					</table>
					<input class="submit" type="submit" value="join">
					</td>
				</tr>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>