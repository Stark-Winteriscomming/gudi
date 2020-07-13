
//domTarget = $j("#search_box"); 
const optionName = "menu"
const domTargetTagName = "div";

function toggle(source) {
	checkboxes = document.getElementsByName("option");
	for (var i = 0; i < checkboxes.length; i++)
		checkboxes[i].checked = source.checked;
}

$j(document).ready(function() {
	
	
	
	$j("#btn-excel").on("click", function() {
		var $preparingFileModal = $j("#preparing-file-modal");
		$preparingFileModal.dialog({ modal: true });
		$j("#progressbar").progressbar({ value: false });
		$j.fileDownload("/board/excelDownload", {
			successCallback: function(url) { $preparingFileModal.dialog('close'); },
			failCallback: function(responseHtml, url) { 
				$preparingFileModal.dialog('close');
				$j("#error-modal").dialog({ modal: true }); 
			}
		});
		// 버튼의 원래 클릭 이벤트를 중지 시키기 위해 필요합니다.
		return false;
	});

	//print the checkboxes into #search_box
	ajaxObj.getOptions("/board/selectBoardType", null, $j("#search_box"), "menu", "div");

	//search with boardtype
	$j("#btn_search").on("click", function() {
		form_value = $j("#frm_search").serialize();
		console.log(form_value);
		var queryUrl = "/board/list?" + form_value;
		$j("#frm_search").attr("action", queryUrl);
		$j("#frm_search").submit();
	}
	);
	
	$j("#btn_canlendar").on("click", function() {
		var $preparingFileModal = $j("#preparing-file-modal");
		$preparingFileModal.dialog({ modal: true });
		$j("#progressbar").progressbar({ value: false });
		$j.fileDownload("/board/calendarDownload", {
			successCallback: function(url) { $preparingFileModal.dialog('close'); },
			failCallback: function(responseHtml, url) { 
				$preparingFileModal.dialog('close');
				$j("#error-modal").dialog({ modal: true }); 
			}
		});
		// 버튼의 원래 클릭 이벤트를 중지 시키기 위해 필요합니다.
		return false;
	})
	
});
