<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
<script type="text/javascript">
	$j(document).ready(
			function() {

				$j("#btn_remove").on("click", function() {
					var $frm = $j('#frm_remove :input');
					var param = $frm.serialize();

					$j.ajax({
						url : "/board/boardRemoveAction.do",
						dataType : "json",
						type : "POST",
						data : param,
						success : function(data, textStatus, jqXHR) {
							alert("삭제완료");

							alert("메세지:" + data.success);

							location.href = "/board/boardList.do?pageNo=1";
						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert("실패");
						}
					});
				});
				//modify
				$j("#btn_modify_complete").on(
						"click",
						function() {

							var $frm = $j('#frm_input :input');
							var param = $frm.serialize();
							console.log(">>" + param);
							$j
									.ajax({
										url : "/board/boardModifyAction.do",
										dataType : "json",
										type : "POST",
										data : param,
										success : function(data, textStatus,
												jqXHR) {
											alert("수정완료");

											alert("메세지:" + data.success);
											//boardType
											//boardNum
											location.href = "/board/"
													+ data.boardType + "/"
													+ data.boardNum
													+ "/boardView.do";

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											alert("실패");
										}
									});

						});
				$j("#btn_modify").on("click", function() {
					//$j(".board-item").css({"border":"none", "border-right":"0px", "border-top":"0px", "boder-left":"0px", "boder-bottom":"0px"});
					//$j(".board-item").css({"outline":"none"});
					$j("#btn_remove").css({"display" : "none"});
					$j("#btn_modify").css({"display" : "none"});
					$j("#btn_modify_complete").css({"display" : "block"});
					$j("#i_board_boardComment").attr('type', 'text');
					$j("#detail_boardComment").empty();
					$j("#i_board_boardTitle").attr('type', 'text');
					$j("#detail_boardTitle").empty();
					
					//$j("#i_board_boardComment").attr("readonly", false);
					//$j("#i_board_title").attr("readonly", false);
				});
			});
</script>
</head>
<body>
	<table align="center">
		<tr>
			<td>
				<form id="frm_input">
					<input type="hidden" name="boardType" value="${boardType }">
					<input type="hidden" name="boardNum" value="${boardNum }">
					<table border="1">
						<tr>
							<td width="120" align="center">Title</td>
							<td width="400">
								<div>
									<input type="hidden" id="i_board_boardTitle"
										class="board-item" name="boardTitle"
										placeholder="${board.boardTitle}">
								</div>
								<div id="detail_boardTitle">${board.boardTitle}</div>
							</td>
						</tr>
						<tr>
							<td height="300" align="center">Comment</td>
							<td>
								<div>
									<input type="hidden" id="i_board_boardComment"
										class="board-item" name="boardComment"
										placeholder="${board.boardComment}">
								</div>
								<div id="detail_boardComment">${board.boardComment}</div>
							</td>
						</tr>
						<tr>
							<td align="center">Writer</td>
							<td></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr><td><button id="btn_remove">remove</button></td></tr>
		<tr><td><button id="btn_modify">modify</button></td></tr>
		<tr><td><button id="btn_modify_complete" style="display : none">작성완료</button></td></tr>
		<tr>
			<td align="right"><a href="/board/boardList.do">List</a></td>
		</tr>
	</table>
	<form id="frm_remove">
		<input type="hidden" name="boardType" value="${boardType}"> <input
			type="hidden" name="boardNum" value="${boardNum}">
	</form>
</body>
</html>