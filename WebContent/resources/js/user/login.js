$j(document).ready(function() {
	$j("#btn_login").on("click", function(){
//		alert($j("#frm_login :input").serialize())
//		var data = {"user_id":"xxx", "user_pw22":"123"};
		afterLoginLink = "/user/login";

		$j.ajax({
			url : "/user/login",
			dataType : "json",
			type : "POST",
			data : $j("#frm_login :input").serialize(),
			success : function(data, textStatus, jqXHR) {
				if(data.msg == 'Y'){
					alert("로그인 성공");
					location.href = afterLoginLink;

				}else {
					alert("로그인 실패");
					location.href = afterLoginLink;
				} 
			},
			error : function(jqXHR, textStatus, errorThrown) {
					alert("서버에러");
			}
		})
	})
})