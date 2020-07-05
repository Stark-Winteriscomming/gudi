const static_element = "<tbody class='tb-board'><tr><td><input type='checkbox' class='cbox-board'></td></tr><tr><td width='120' align='center'>Type</td><td width='400'><select class='sel-board-type'></select></td></tr><tr><td width='120' align='center'>Title</td><td width='400'><input class='i-board-title' type='text' size='50'></td></tr><tr><td width='120' align='center'>Comment</td><td width='400'valign='top'><textarea class='t-board-comment'></textarea></td></tr></tbody>"
	

$j(document).ready(function() {
	function initAddedElement(element){
		$j("#tbl_board_write").append(static_element);
		const tb = document.getElementsByClassName("tb-board");
		const len = tb.length;
		console.log($j(tb[len-1]).find(".sel-board-type"));
		console.log(`tb-board len: ${len}`);
		const name_value = 'list[' + parseInt(len-1) + '].boardType';
		$j(tb[len-1]).find(".sel-board-type").attr('name', 'list[' + parseInt(len-1) + '].boardType');
		$j(tb[len-1]).find(".i-board-title").attr('name', 'list['+ parseInt(len-1) + '].boardTitle');
		$j(tb[len-1]).find(".t-board-comment").attr('name', 'list['+ parseInt(len-1) + '].boardComment');
		ajaxObj.getOptions("/board/selectBoardType", null, $j(tb[len-1]).find(".sel-board-type"), "menu", "select");
	}
	
	$j(".btn_plus").on("click", function(e) {
		initAddedElement(static_element);
	})
	$j(".btn_rm").on("click", function(e) {
		$j(".cbox-board:checked").parent().parent().parent().remove();
	})

	//print the options into #sel_board_type
	ajaxObj.getOptions("/board/selectBoardType", null, $j(".sel-board-type"), "menu", "select");

	$j("#submit").on("click", function() {
		console.log('#submit").on(click')
		ajaxObj.updateData("/board/write", $j('#tbl_board_write tbody:visible :input').serialize(), "POST", "write")
	});
});
