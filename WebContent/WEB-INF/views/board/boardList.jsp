<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">
	function toggle(source) {
	  checkboxes = document.getElementsByName("option");
	  for(var i=0; i<checkboxes.length; i++)
		  checkboxes[i].checked = source.checked;
	}
	
	
	$j(document).ready(function(){
		
		
		(function fn_get_select_item(param) {
			$j.ajax({	
				url : "/board/selectBoardType.do",
				dataType : "json",
				type : "GET",
				data : param,
				success : function(data, textStatus, jqXHR) {
					for(var i=0; i<data.length; i++){
						$j("#search_box").append("<input type='checkbox' name='option' value=" + data[i].code_id + " />" + 
							      "<label for=" + data[i].code_id + ">" + data[i].code_name + "</label>");
					}
					// any checkbox clicked
					$j("input[name=option]").on("click", function () {
						if($j("input[name=option]:checked").length == $j("input[name=option]").length){
							$j("input[name=total]").prop("checked", true);
						}else {
							$j("input[name=total]").prop("checked", false);
						}
					});
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("실패");
				}
			});
			
		})();
		//
		
		//
		$j("#btn_search").on("click", function () {
			form_value = $j("#frm_search").serialize();
			console.log(form_value);
			var queryUrl = "/board/boardList.do?" + form_value;
			$j("#frm_search").attr("action", queryUrl);
			$j("#frm_search").submit();
		});
		
		
		
	});
	
</script>
<body>
	<table align="center">
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
				<a href="/board/boardWrite.do">글쓰기</a>
			</td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" name="total" value="total" onClick="toggle(this)" /> <label for="total">전체</label>
				<form id=frm_search>
					<div id="search_box">
					</div>
					<input type="submit" value="조회" style="display : none">
				</form>
				<button id="btn_search">조회</button>
<!-- 				<a id="a_search" href="">조회</a> -->
			</td>
		</tr>
	</table>
</body>
</html>