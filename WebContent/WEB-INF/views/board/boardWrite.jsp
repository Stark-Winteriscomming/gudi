<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">
	$j(document).ready(function() {
		////////
		(function fn_get_select_item(param) {
		var target = $j("#sel_board_type");
		target.empty(); 
		
// 		if(param == "") {
// 			target.append("<option value=" + "'" + "선택" + "'" + "/>" );
// 		}
		$j.ajax({	
			url : "/board/selectBoardType.do",
			dataType : "json",
			type : "GET",
			data : param,
			success : function(data, textStatus, jqXHR) {

				console.log(data);
				for (i = 0; i < data.length; i++) {
					console.log(data[i].code_name);
					target.append("<option value=" + "'" + data[i].code_name + "'>" + data[i].code_name +"</option>" );
				}
					

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("실패");
			}
		});

	})();
///////////
		
		$j("#submit").on("click", function() {
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			$j.ajax({
				url : "/board/boardWriteAction.do",
				dataType : "json",
				type : "POST",
				success : function(data, textStatus, jqXHR) {
					alert("작성완료");

					alert("메세지:" + data.success);

					location.href = "/board/boardList.do?pageNo=1";
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("실패");
				}
			});
		});
		// select box on changed
// 		$j("select[name='boardType']").on("change", function(source){
// 			console.log(this.value); 
// 		});
		
		

	});

</script>
<body>
	<form class="boardWrite">
		<table align="center">
			<tr>
				<td align="right"><input id="submit" type="button" value="작성">
				</td>
			</tr>
			<tr>
				<td>
<!-- 				fn_get_select_item(this.value) -->
					<table border="1">
						<tr>
							<td width="120" align="center">Type</td>
							<td width="400"><select name="boardType"
								id="sel_board_type">
									<option value="">선택</option>
							</select></td>
						</tr>
						<tr>
							<td width="120" align="center">Title</td>
							<td width="400"><input name="boardTitle" type="text"
								size="50" value="${board.boardTitle}"></td>
						</tr>
						<tr>
							<td height="300" align="center">Comment</td>
							<td valign="top"><textarea name="boardComment" rows="20"
									cols="55">${board.boardComment}</textarea></td>
						</tr>
						<tr>
							<td align="center">Writer</td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right"><a href="/board/boardList.do">List</a></td>
			</tr>
		</table>
	</form>
</body>
</html>