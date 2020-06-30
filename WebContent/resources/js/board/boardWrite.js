$j(document).ready(function() {
	$j("tbody")[2].style.display = "none";
	$j("tbody")[3].style.display = "none";

	$j(".btn_plus").on("click", function(e) {
		//보여지는 갯수
		const len = $j("#tbl_board_write").data('len');
		if (len == 3) {
			alert('max'); return;
		} else {
			$j(".tb-board")[len].style.display = "";
			$j("#tbl_board_write").data('len', len + 1);
		}

	})
	$j(".btn_rm").on("click", function(e) {
		const len = $j("#tbl_board_write").data('len');
		if (len == 1) {
			alert('no rm'); return;
		} else {
			const visiable_tb_len = $j(".tb-board:visible").length; 
			
			const t_cboxs = $j(".cbox-board");
			const cboxs = $j(".cbox-board:checked");
			console.log(`cboxs len: ${cboxs.length}`)
			for (let i = 0; i < cboxs.length; i++) {
				const index = parseInt(cboxs[i].dataset.index);
				if(visiable_tb_len == 3 && cboxs[i].dataset.index == '1' && cboxs.length != 2){
					console.log(`index : ${index}`)
					const c = $j(".tb-board")[2].children;
					const type = $j(c[1]).find('select option:selected').val();
					const title = $j(c[2]).find('input[name=boardTitle]').val();
					const content = $j(c[3]).find('textarea').val(); 
					
					const c2 = $j(".tb-board")[1].children;
					console.log(c2[1])
					$j(c2[1]).find('select option[value=' + type + ']').attr('selected', 'selected');
					$j(c2[2]).find('input[name=boardTitle]').val(title);
					$j(c2[3]).find('textarea').val(content);
					
					$j(c[1]).find('select option:selected').val("");
					$j(c[2]).find('input[name=boardTitle]').val("");
					$j(c[3]).find('textarea').val("");
					
					$j(".tb-board")[2].style.display = "none"
					t_cboxs[index-1].checked = false;
					$j("#tbl_board_write").data('len', len - cboxs.length);
					return;
				}
				t_cboxs[index-1].checked = false;
				const r = $j(".tb-board")[index].children;
//				$j(r[1]).find('select option:selected').val("");
				console.log($j(r[1]).find('select option[value=opt_default]'))
				$j(r[1]).find('select option[value=opt_default]').attr('selected', 'selected');
				$j(r[2]).find('input[name=boardTitle]').val("");
				$j(r[3]).find('textarea').val("");
				$j(".tb-board")[index].style.display = "none";
			}
			$j("#tbl_board_write").data('len', len - cboxs.length);
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
