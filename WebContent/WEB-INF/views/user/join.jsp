<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>join view</title>

<script>
$j(document).ready(function() {
	$j("#commentForm").validate({
		rules: {
			user_pw_check : {
				pwSameCheck: true
			},
			user_addr1 :{
				postNumCheck : true
			}
		}, 
// 		message:{
// 			user_pw_check : {
// 				domain: "not equal"
// 			}		
// 		}
	});
	
	//
	
	$j.validator.addMethod("pwSameCheck", function(value, element) {
  return this.optional(element) || value == $j("input[name=user_pw]").val();
}, "not equal..");
	
	$j.validator.addMethod("postNumCheck", function(value, element) {
		  return this.optional(element) || /^[0-9][0-9][0-9]-[0-9][0-9][0-9]$/.test(value);
		}, "not addr1 format..");
	
	(function fn_get_select_item(param) {
	var target = $j("#sel_user_phone1");
	target.empty(); 
	
	$j.ajax({	
		url : "/user/selectPhoneType.do",
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
});
</script>
</head>
<body>
	<form class="cmxform" id="commentForm" method="get" >
	<fieldset>
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
									<input type="text" name="user_pw" minlength="6" maxlength="12">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw check</td>
							<td width="300">
								<div>
									<input type="text" name="user_pw_check">
								</div>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">name</td>
							<td width="300">
								<div>
									<input type="text" name="user_name" required>
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
									<input type="number" size="4" name="user_phone2"  required minlength=4 maxlength=4 /> <input type="number" size="4" name="user_phone3" required />
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
					<input class="submit" type="submit" value="Submit">
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
	</fieldset>
	</form>
</body>
</html>