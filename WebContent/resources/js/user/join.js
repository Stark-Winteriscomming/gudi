function maxLengthCheck(object, length){
    if (object.value.length > length){
      object.value = object.value.slice(0, length);
    }    
}

$j(document).ready(function() {
	
	 $j("#user_id").on('focusout',function(e){
      e.stopPropagation();
    });

	//phonen 4자리 validation 
	$j("#user_phone2, #user_phone3").on("focusout", function(){
		if(!($j("#user_phone2").val().length == 4 && $j("#user_phone3").val().length == 4)){
			$j("#error_msg").text("4자리만 가능"); 
		}else $j("#error_msg").text("");
	})
	
//	$j("#user_phone3").on("focusout", function(){
//		if(!($j("#user_phone2").val().length == 4 && $j("#user_phone3").val().length == 4)) console.log('nn')
//	})
	
	ajaxObj.getOptions("/user/selectPhoneType/", null, $j("#sel_user_phone1"), "phone", "select"); 
	//
	$j("#btn_duplicate_check").on("click", function(){
		const userObj = $j("input[name=user_id]");
		const user_id = userObj.val()
		
		if(user_id == ''){
			alert('아이디를 입력해주세요');
			return false;
		}
		
		$j.ajax({	
			url : "/user/check/dupe/" + user_id,
			dataType : "json",
			type : "GET",
			success : function(data, textStatus, jqXHR) {
				if(data.msg == 'dupe'){
					$j("input[name=user_id]").data("check", "unchecked");
					alert("중복됨 다른 아이디 입력바람") 
				}else{
					$j("input[name=user_id]").data("check", "checked");
					alert("가능")
				}
//				 ? "가능" : "중복!";
//				alert(msg);
//				data.leftTime
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("실패");
			}
		});	
	})
	
 
	$j("#frm_join").validate({
//		onfocusout : function(element){
//			var element_id = $(element).attr('id');
//        	if (element_id == 'user_id') {
//            $.validator.defaults.onkeyup.apply(this, arguments);
//        }	
//		},
		rules: {
			user_pw_check : {
				pwSameCheck: true
			},
			user_check : {
				pwSameCheck: true
			},
			user_addr1 : {
				postNumCheck : true
			}, 
			user_id : {
				engCheck : true,
				dupCheck : true,
				onfocusout : true
			},
			user_name : {
				hanCheck : true,
				required : true
			}
		}, 
// 		message:{
// 			user_pw_check : {
// 				domain: "not equal"
// 			}, 
//			user_id : "중복체크해라"		
// 		}
	});
	
	//
	
	$j.validator.addMethod("pwSameCheck", function(value, element) {
  return value == $j("input[name=user_pw]").val();
}, "비밀번호 불일치");
	
	$j.validator.addMethod("postNumCheck", function(value, element) {
		  return this.optional(element) || /^[0-9][0-9][0-9]-[0-9][0-9][0-9]$/.test(value);
		}, "형태: xxx-xxx => x는 숫자, ex) 123-123 " );
		
	$j.validator.addMethod("hanCheck", function(value, element) {
		  return /^[가-힣]+$/.test(value);
		}, "한글음절만 가능(최대 4글자)" );
		
	$j.validator.addMethod("engCheck", function(value, element) {
		  return /^[a-zA-Z0-9]*$/ .test(value);
		}, "영어, 숫자만 기술" )
	$j.validator.addMethod("dupCheck", function(value, element) {
		  return (($j("input[name=user_id]").data("check")) =='checked' ? true : false);
		}, "중복확인 바람" )
});