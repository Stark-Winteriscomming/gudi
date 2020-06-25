$j(document).ready(function() {
	const fn_plus = function() {
		$j(".btn_plus").on("click", function() {
			//		v = $j("#tb_board_write").html();

			v = $j("#tbl_board_write").html();
			$j("#tbl_board_write").append(v);
			fn_plus();
			fn_rm();
		})
	}
	fn_plus();
	const fn_rm = function() {
		$j(".btn_rm").on("click", function(e) {
			//			console.log(e);
			//			$j(e.currentTarget).parent().parent().parent().remove();
			$j(e.currentTarget).parent().parent().parent().remove();
		})
	}
	//print the options into #sel_board_type
	ajaxObj.getOptions("/board/selectBoardType", null, $j("#sel_board_type"), "menu", "select");

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
			if(!($j("input[name='boardTitle']").val())){
				alert("제목을 작성해주세요")
				return false;	
			}
			if(!($j("textarea[name='boardComment']").val())){
				alert("내용을 작성해주세요")
				return false;	
			}
			if(($j("#sel_board_type option:selected").val()) == '선택'){
				alert("타입을 선택해주세요")
				return false;	
			}
			//updateData : function(url, param, type)
			ajaxObj.updateData("/board/write", $j('.boardWrite :input').serialize(),"POST","write")
	});
});
