function maxLengthCheck(object, length){
    if (object.value.length > length){
      object.value = object.value.slice(0, length);
    }    
}

$j(document).ready(function() {
	$j("#user_phone2, #user_phone3").on("propertychange change paste input", function(){
		if(($j("#user_phone2").val().length) == 4 && ($j("#user_phone3").val().length == 4)){
			$j("#lbl_phone").text("");
		}else if(($j("#user_phone2").val() != '') || ($j("#user_phone3").val() != '')){
			$j("#lbl_phone").text("중간, 뒷자리는 4자리 고정");
		}
	});
//	//phonen 4자리 validation 
//	$j("#user_phone2, #user_phone3").on("focusout", function(){
//		if(!($j("#user_phone2").val().length == 4 && $j("#user_phone3").val().length == 4)){
//			$j("#error_msg").text("4자리만 가능"); 
//		}else $j("#error_msg").text("");
//	})
	
//	$j("#user_phone3").on("focusout", function(){
//		if(!($j("#user_phone2").val().length == 4 && $j("#user_phone3").val().length == 4)) console.log('nn')
//	})
	
	ajaxObj.getOptions("/user/selectPhoneType/", null, $j("#sel_user_phone1"), "phone", "select"); 
	//
	$j("#btn_reset").on("click", function(){
		$j(".error").text("");
		$j("#frm_join")[0].reset();
		ajaxObj.getOptions("/user/selectPhoneType/", null, $j("#sel_user_phone1"), "phone", "select");
		$j(".phone-error").css("display", "none"); 
	})
	
	//
	$j("#btn_duplicate_check").on("click", function(){
		const userObj = $j("input[name=user_id]");
		const user_id = userObj.val()
		if(/^[a-zA-Z0-9]*$/.test(user_id) == false){
			alert("숫자와 영문만가능");
			return false;
		} 
		if(user_id == ''){
			alert('아이디를 입력해주세요');
			return false;
		}
		
		$j.ajax({	
			url : "/user/check/dupe/" + user_id,
			dataType : "json",
			type : "GET",
			success : function(data, textStatus, jqXHR) {
				const userIdObj = $j("input[name=user_id]");
				if(data.msg == 'dupe'){
					userIdObj.data("check", "unchecked");
					alert("중복됨 다른 아이디 입력바람") 
				}else{
					userIdObj.data("check", "checked");
					$j("#lbl_user_id").text("가능");
					userIdObj.on("propertychange change paste input", function(){
						userIdObj.data("check", "unchecked");
						$j("#lbl_user_id").text("");	
						userIdObj.off("propertychange change paste input");
					})
					alert("가능")
				}
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
//		ignore : ".ignore",
		onfocusout : function (element){
			if($j(element).attr("id") == "user_id"){
				return false;
			}else return true;
		},
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
				required : true,
				engCheck : true,
				dupCheck : true

			},
			user_name : {
				hanCheck : true,
				required : true
			},
			user_phone1 : {
				isSelected : true
			},
			user_phone2 : {
				required : true,
				lengthCheck :true
			},
			user_phone3 : {
				required : true,
				lengthCheck :true
			}, 
		},
		submitHandler: function(form) {
			const idObj =  $j("input[name=user_id]");
			if(idObj.data("check") == 'checked'){
				form.submit();
			}else{
				alert('아이디 중복체크하세요');
				idObj.focus();
			} 
		},
		invalidHandler: function(event, validator){
			var errors = validator.numberOfInvalids();
			if(errors){
				if($j("input[name=user_id]").val() == ''){
					$j("#lbl_user_id").text("필수 항목입니다.");
					//$j("input[name=user_id]").prop('required',true);
					//$j("#frm_join").valid();
				}
				if(($j("input[name=user_id]").val() != '') && ($j("input[name=user_id]").data("check") =='unchecked')){
					$j("#lbl_user_id").text("중복확인필요");
				}
				if(($j("#user_phone2").val() == '') && ($j("#user_phone3").val() == '')){
					$j("#lbl_phone").text("필수 항목입니다.");
				}else if(($j("#user_phone1 > option:selected").val() != 'default') && ($j("#user_phone2").val().length != 4) && ($j("#user_phone3").val().length != 4)){
					$j("#lbl_phone").text("앞자리는 꼭 선택, 중간, 뒷자리는 4자리 고정");
				}
			}
		}

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
		}, "중복체크 바람." )
	$j.validator.addMethod("phoneCheck", function(value, element) {
		  return (($j("#user_phone1 > option:selected").val() != 'default') && ($j("#user_phone2").val().length) == 4 && ($j("#user_phone3").val().length == 4));
		}, "" )
	$j.validator.addMethod("isSelected", function(value, element) {
		  return (($j("#sel_user_phone1 > option:selected").val() != 'default'))
		}, "앞자리 선택," )
	$j.validator.addMethod("lengthCheck", function(value, element) {
		  return (value.length == 4)
		}, "4자리 입력," )
	
});