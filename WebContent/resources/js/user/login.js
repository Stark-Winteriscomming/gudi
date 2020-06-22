function maxLengthCheck(object, length) {
	if (object.value.length > length) {
		alert("최대 값: " + length + " 초과");
		object.value = object.value.slice(0, length);
	}
}

$j(document).ready(function() {
	$j("#btn_login").on("click", function(){
		afterLoginLink = "/board/list";
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
					if(data.idExistMsg == "exist"){
						alert("비밀번호 불일치");
						$j("input[name=user_pw]").focus();
					}else{
						alert("아이디 불일치");
					}
					//location.href = afterLoginLink;
				} 
			},
			error : function(jqXHR, textStatus, errorThrown) {
					alert("서버에러");
			}
		})
	})
})