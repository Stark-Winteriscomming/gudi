$j(document).ready(function() {
	$j("#btn_login").on("click", function(){
		alert($j("#frm_login :input").serialize())
		$j.ajax({	
			url : "/user/login",
			dataType : "json",
			type : "POST",
			data : $j("#frm_login :input").serialize(),
			success : function(data, textStatus, jqXHR) {
				if(data.msg == 'Y'){
					alert("로그인 성공");
				}else alert("로그인 실패") 
			},
			error : function(jqXHR, textStatus, errorThrown) {
					alert("서버에러");
			}
		})
	})
})