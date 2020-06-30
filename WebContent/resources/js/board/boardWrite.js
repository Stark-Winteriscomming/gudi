$j(document).ready(function() {
//	$j("tbody")[2].style.display = "none";
//	$j("tbody")[3].style.display = "none";
	
	$j(".btn_plus").on("click", function(e) {
		const len = $j("#tbl_board_write").data('len');
		if(len == 3){
			alert('max');return;
		}else {
			$j("tbody")[len + 1].style.display = "";
			$j("#tbl_board_write").data('len', len + 1);
		}
		
	})
	$j(".btn_rm").on("click", function(e) {
		const len = $j("#tbl_board_write").data('len');
		if(len == 1){
			alert('no rm');return;
		}else {
			$j("tbody")[len].style.display = "none";
			$j("#tbl_board_write").data('len', len - 1);
		}
	})

	//print the options into #sel_board_type
	ajaxObj.getOptions("/board/selectBoardType", null, $j(".sel-board-type"), "menu", "select");

	$j("#submit").on("click", function() {
		if (!($j("input[name='boardTitle']").val())) {
			alert("제목을 작성해주세요")
			return false;
		}
		if (!($j("textarea[name='boardComment']").val())) {
			alert("내용을 작성해주세요")
			return false;
		}
		if (($j("#sel_board_type option:selected").val()) == '선택') {
			alert("타입을 선택해주세요")
			return false;
		}
		//updateData : function(url, param, type)
		ajaxObj.updateData("/board/write", $j('.boardWrite :input').serialize(), "POST", "write")
		if (!($j("input[name='boardTitle']").val())) {
			alert("제목을 작성해주세요")
			return false;
		}
		if (!($j("textarea[name='boardComment']").val())) {
			alert("내용을 작성해주세요")
			return false;
		}
		if (($j("#sel_board_type option:selected").val()) == '선택') {
			alert("타입을 선택해주세요")
			return false;
		}
		//updateData : function(url, param, type)
		ajaxObj.updateData("/board/write", $j('.boardWrite :input').serialize(), "POST", "write")
	});
});
