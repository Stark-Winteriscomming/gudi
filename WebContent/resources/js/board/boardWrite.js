$j(document).ready(function() {
	// 복사될 고정 요소
//	const dupe_content = $j("#tbl_board_write").html();
	const add_string = "<tr id='tr_btn_esc'><td colspan='2' align='right' style='border: 0'>"
	+ "<button class='btn_rm'>X</button><td></tr>";
	
	$j(".tr_btn_esc").detach();
	
	const fn_plus = function() {
		$j(".btn_plus").on("click", function(e) {
			$j("#tbl_board_write").append(dupe_content);
			// 내용을 다시 만들고 click event를 만들어야 하므로 fun_plus() 다시호출
			
			fn_plus();
			fn_rm();
			const len = $j(".tr_btn_plus").length;
			console.log(`len: ${len}`);
			
			for(let i=0; i<len; i++){
				if(i == 0){
					$j(".tr_btn_plus")[0].style.display = "none";
				}
				else if(i != len-1){
					$j(".tr_btn_plus")[i].remove();	
				}
			}
//			if(len == 3){
//				$j(".tr_btn_plus")[len-1].style.display = "none"	
//			}
		})
	}
	const fn_rm = function() {
		$j(".btn_rm").on("click", function(e) {
			console.log(dupe_content)
			$j(e.currentTarget).parent().parent().parent().remove();
			console.log('------------------------------------')
			console.log(dupe_content)
			const len = $j(".tr_btn_plus").length;
			console.log(`len: ${len}`)
			if($j(".tr_btn_plus").length == 1){
				$j(".tr_btn_plus")[0].style.display = "";
			}
//			if($j("tbody").length == 1){
//				
//			}
		})
	}
	
	//init set up
	fn_plus();
	fn_rm();
	
	
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
