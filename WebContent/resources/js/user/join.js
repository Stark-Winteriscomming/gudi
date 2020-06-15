function maxLengthCheck(object){
    if (object.value.length > 4){
      object.value = object.value.slice(0, 4);
    }    
}


$j(document).ready(function() {
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
		const user_id = $j("input[name=user_id]").val()
		console.log("userid: " + user_id);
		$j.ajax({	
			url : "/user/check/dupe/" + user_id,
			dataType : "json",
			type : "GET",
			success : function(data, textStatus, jqXHR) {
				alert(data.leftTime); 
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("실패");
			}
		});	
	})
	
	 
	$j("#commentForm").validate({
		rules: {
			user_pw_check : {
				pwSameCheck: true
			},
			user_addr1 : {
				postNumCheck : true
			}, 
			user_id : {
				engCheck : true
			},
		}, 
// 		message:{
// 			user_pw_check : {
// 				domain: "not equal"
// 			}		
// 		}
	});
	
	//
	
	$j.validator.addMethod("pwSameCheck", function(value, element) {
  return value == $j("input[name=user_pw]").val();
}, "비밀번호 불일치");
	
	$j.validator.addMethod("postNumCheck", function(value, element) {
		  return this.optional(element) || /^[0-9][0-9][0-9]-[0-9][0-9][0-9]$/.test(value);
		}, "형태: xxx-xxx => x는 숫자, ex) 123-123 " );
		
	$j.validator.addMethod("engCheck", function(value, element) {
		  return /^[a-zA-Z0-9]*$/ .test(value);
		}, "영어, 숫자만 기술" )
});