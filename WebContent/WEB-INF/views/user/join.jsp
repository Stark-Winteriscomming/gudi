<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>join view</title>

<script>
(function fn_get_select_item(param) {
	var target = $j("#sel_user_phone1");
	target.empty(); 
	
	$j.ajax({	
		url : "/user/phone/type",
		dataType : "json",
		type : "GET",
		data : param,
		success : function(data, textStatus, jqXHR) {
			for (i = 0; i < data.length; i++) {
				target.append("<option value=" + "'" + data[i].code_id + "'>" + data[i].code_name +"</option>" );
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("실패");
		}
	});	
})();
</script>
</head>
<body>
	<table align="center">
		<tr>
			<td>
				<form id="frm_input">
					<table border="1">
						<tr>
							<td width="120" align="center">id</td>
							<td width="300">
								<div>
									<input type="text" name="user_id">
									<button id="btn_duplicate_check">중복확인</button>
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw</td>
							<td width="300">
								<div>
									<input type="text" name="user_pw">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw check</td>
							<td width="300">
								<div>
									<input type="text" name="user_pw">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">name</td>
							<td width="300">
								<div>
									<input type="text" name="user_name">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">phone</td>
							<td width="300">
								<div>
									<select id="sel_user_phone1">
									</select>
									<input type="text" size="4" /> <input type="text" size="4" />
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
							<td width="300">
								<div>
									<input type="text" name="user_company">
								</div>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<button id="btn_join">join</button>
			</td>
		</tr>
		<tr>
			<td align="right">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>
	<form id="frm_remove">
		<input type="hidden" name="boardType" value="${boardType}"> <input type="hidden" name="boardNum" value="${boardNum}">
	</form>
</body>
</html>