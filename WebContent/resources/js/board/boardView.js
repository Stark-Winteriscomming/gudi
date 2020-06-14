//updateData : function(url, param, type)

$j(document).ready(function() {
    $j("#btn_remove").on("click", function() {
        var param = $j('#frm_remove :input').serialize();
        ajaxObj.updateData("/board/boardRemoveAction.do", param, "POST", "rm");
    });
    
    //modify
    $j("#btn_modify_complete").on("click", function() {
        var param = $j('#frm_input :input').serialize();
        ajaxObj.updateData("/board/boardModifyAction.do", param, "POST", "update")
    });
    
    $j("#btn_modify").on("click", function() {
        $j("#btn_remove").css({
            "display": "none"
        });
        $j("#btn_modify").css({
            "display": "none"
        });
        $j("#btn_modify_complete").css({
            "display": "block"
        });
        $j("#i_board_boardComment").attr('type', 'text');
        $j("#detail_boardComment").empty();
        $j("#i_board_boardTitle").attr('type', 'text');
        $j("#detail_boardTitle").empty();
    });
});