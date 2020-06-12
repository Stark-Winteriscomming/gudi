$j(document).ready(function() {
    $j("#btn_remove").on("click", function() {
        var $frm = $j('#frm_remove :input');
        var param = $frm.serialize();

        $j.ajax({
            url: "/board/boardRemoveAction.do",
            dataType: "json",
            type: "POST",
            data: param,
            success: function(data, textStatus, jqXHR) {
                alert("삭제완료");

                alert("메세지:" + data.success);

                location.href = "/board/boardList.do?pageNo=1";
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("실패");
            }
        });
    });
    //modify
    $j("#btn_modify_complete").on("click", function() {

        var $frm = $j('#frm_input :input');
        var param = $frm.serialize();
        console.log(">>" + param);
        $j.ajax({
            url: "/board/boardModifyAction.do",
            dataType: "json",
            type: "POST",
            data: param,
            success: function(data, textStatus,
                jqXHR) {
                alert("수정완료");

                alert("메세지:" + data.success);
                //boardType
                //boardNum
                location.href = "/board/" +
                    data.boardType + "/" +
                    data.boardNum +
                    "/boardView.do";

            },
            error: function(jqXHR, textStatus,
                errorThrown) {
                alert("실패");
            }
        });

    });
    $j("#btn_modify").on("click", function() {
        //$j(".board-item").css({"border":"none", "border-right":"0px", "border-top":"0px", "boder-left":"0px", "boder-bottom":"0px"});
        //$j(".board-item").css({"outline":"none"});
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

        //$j("#i_board_boardComment").attr("readonly", false);
        //$j("#i_board_title").attr("readonly", false);
    });
});