$j(document).ready(function() {
	//print the options into #sel_board_type
	ajaxObj.getOptions("/board/selectBoardType", null, $j("#sel_board_type"), "menu", "select");
	
	
	$j("#submit").on("click", function() {
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
			ajaxObj.updateData("/board/write", $j('.boardWrite :input').serialize(),"POST")
	});
});