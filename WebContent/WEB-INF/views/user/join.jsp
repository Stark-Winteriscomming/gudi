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
<style type="text/css">
label {
	font-size: 10px;
	color : red;
}
</style>
</head>
<body>
	<form class="cmxform" id="frm_join" method="post" action="/user/join">
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
									<input type="text" class="ignore" id="user_id" name="user_id" data-check="unchecked"> <input type="button" id="btn_duplicate_check" value="중복확인"> 
									<label class="error" id="lbl_user_id" for="user_id"></label>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw</td>
							<td width="400">
								<input type="text" name="user_pw" minlength="6" maxlength="12" required>
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
							<td width="120" align="center" rowspan="2">phone</td>
							<td width="300">
								<div>
									<select id="sel_user_phone1" name="user_phone1">
										<option value="default" selected>선택</option>
									</select>
									<input type="number" style="width: 60px" id="user_phone2" name="user_phone2" fix=4 oninput="maxLengthCheck(this, 4)" /> 
									<input type="number" style="width: 60px" id="user_phone3" name="user_phone3" fix=4 oninput="maxLengthCheck(this, 4)" /> 
								</div>
							</td>
						</tr>
						<tr>
<!-- 							<td width="120" align="center">phone</td> -->
							<td width="300">
<!-- 								display:none; -->
									<label class="error phone-error" style="font-size: 10px;display: none" for="sel_user_phone1"></label>
									<label class="error phone-error" style="font-size: 10px;display: none" for="user_phone2"></label>
									<label class="error phone-error" style="font-size: 10px;display: none" for="user_phone3"></label>
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
					<input type="button" id="btn_reset" value="reset">
					</td>
				</tr>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>