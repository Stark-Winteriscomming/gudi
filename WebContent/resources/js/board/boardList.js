
//domTarget = $j("#search_box"); 
const optionName = "menu"	
const domTargetTagName = "div";	

function toggle(source) {
		checkboxes = document.getElementsByName("option");
		for (var i = 0; i < checkboxes.length; i++)
			checkboxes[i].checked = source.checked;
}

$j(document).ready(function() {
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
});
