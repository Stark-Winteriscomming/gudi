function maxLengthCheck(object, length) {
	if (object.value.length > length) {
		alert(length + "초과 불가")
		object.value = object.value.slice(0, length);
	}
}

$j(document).ready(function() {
	$j("#user_addr1").on("input", function(e) {
		const obj = $j("#user_addr1");
		let len = obj.val().length;
		const input = obj.val()[len - 1];
		if (/[0-9]/.test(input) == false) {
			obj.val(obj.val().substring(0, len - 1));
		}
		len = obj.val().length;
		if (len == 4) {
			let stmt = obj.val();
			let tmp = obj.val()[3];
			let c = stmt.slice(0, 3);
			let s = c + "-" + tmp;
			obj.val(s);
		}
		if (len == 8) {
			obj.val(obj.val().substring(0, len - 1));
			alert('6자리 초과 할 수 없음');
		}
	})
	$j("#user_id").on('input', function(e) {
		v = $j("#user_id").val();
		if (v.length > 14) {
			alert('14자 초과 안됨');
			$j("#user_id").val(v.substring(0, v.length - 1))
			return false;
		}
		if ((/[ㄱ-ㅊㅏ-ㅣ가-힣]+/g.test(v)) == true) {
			alert('한글입력안됨');
			$j("#user_id").val(v.replace(/[ㄱ-ㅊㅏ-ㅣ가-힣]+/g, ''))
		}
	})
	$j("#user_name").on('input', function(e) {
		v = $j("#user_name").val();
		if (v.length > 4) {
			alert('4자 초과 안됨');
			return false;
		}
		if ((/[a-z0-9]+/gi.test(v)) == true) {
			alert('한글만 입력');
			$j("#user_name").val(v.replace(/[a-z0-9]+/gi, ''))
		}
	})

	$j("#user_phone2, #user_phone3").on("propertychange change paste input keyup", function(e) {
		const len = $j(this).val().length;
		if (e.type == "keyup") {
			var code = e.keyCode || e.which;
			const v = String.fromCharCode(code);
			if (/[0-9]/.test(v) == false && code != 8) {
				alert('숫자만 입력');
				if (len == 1) {
					$j(this).val('');
				}
				else {
					$j(this).val(($j(this).val().substring(0, len)))
				}
			}
		}

	});

	ajaxObj.getOptions("/user/selectPhoneType/", null, $j("#sel_user_phone1"), "phone", "select");
	//
	$j("#btn_reset").on("click", function() {
		$j(".error").text("");
		$j("#frm_join")[0].reset();
		ajaxObj.getOptions("/user/selectPhoneType/", null, $j("#sel_user_phone1"), "phone", "select");
		$j(".phone-error").css("display", "none");
	})

	//
	$j("#btn_duplicate_check").on("click", function() {
		const userObj = $j("input[name=user_id]");
		const user_id = userObj.val()
		if (/^[a-zA-Z0-9]*$/.test(user_id) == false) {
			alert("숫자와 영문만가능");
			return false;
		}
		if (user_id == '') {
			alert('아이디를 입력해주세요');
			userObj.focus();
			return false;
		}

		$j.ajax({
			url: "/user/check/dupe/" + user_id,
			dataType: "json",
			type: "GET",
			success: function(data, textStatus, jqXHR) {
				const userIdObj = $j("input[name=user_id]");
				if (data.msg == 'dupe') {
					userIdObj.data("check", "unchecked");
					alert("중복됨 다른 아이디 입력바람");
					userIdObj.focus();
				} else {
					userIdObj.data("check", "checked");
					$j("#lbl_user_id").text("가능");
					userIdObj.on("propertychange change paste input", function() {
						userIdObj.data("check", "unchecked");
						$j("#lbl_user_id").text("");
						userIdObj.off("propertychange change paste input");
					})
					alert("가능")
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("실패");
			}
		});
	})

	$j("#frm_join").validate({
		onfocusout: function(element) {
			if ($j(element).attr("id") == "user_id") {
				return false;
			} else return true;
		},
		rules: {
			user_pw_check: {
				pwSameCheck: true
			},
			user_check: {
				pwSameCheck: true
			},
			user_addr1: {
				postNumCheck: true
			},
			user_id: {
				required: true,
				engCheck: true,
				dupCheck: true

			},
			user_name: {
				hanCheck: true,
				required: true
			},
			user_phone1: {
				isSelected: true
			},
			user_phone2: {
				required: true,
				lengthCheck: true
			},
			user_phone3: {
				required: true,
				lengthCheck: true
			},
		},
		submitHandler: function(form) {
			const idObj = $j("input[name=user_id]");
			if (idObj.data("check") == 'checked') {
				form.submit();
			} else {
				alert('아이디 중복체크하세요');
				idObj.focus();
			}
		},
		invalidHandler: function(event, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				if ($j("input[name=user_id]").val() == '') {
					$j("#lbl_user_id").text("필수 항목입니다.");
				}
				if (($j("input[name=user_id]").val() != '') && ($j("input[name=user_id]").data("check") == 'unchecked')) {
					$j("#lbl_user_id").text("중복확인필요");
				}
				if (($j("#user_phone2").val() == '') && ($j("#user_phone3").val() == '')) {
					$j("#lbl_phone").text("필수 항목입니다.");
				} else if (($j("#user_phone1 > option:selected").val() != 'default') && ($j("#user_phone2").val().length != 4) && ($j("#user_phone3").val().length != 4)) {
					$j("#lbl_phone").text("앞자리는 꼭 선택, 중간, 뒷자리는 4자리 고정");
				}
			}
		}
	});

	//

	$j.validator.addMethod("pwSameCheck", function(value, element) {
		return value == $j("input[name=user_pw]").val();
	}, "비밀번호 불일치");

	$j.validator.addMethod("postNumCheck", function(value, element) {
		return this.optional(element) || /^[0-9][0-9][0-9]-[0-9][0-9][0-9]$/.test(value);
	}, "형태: xxx-xxx => x는 숫자, ex) 123-123 ");

	$j.validator.addMethod("hanCheck", function(value, element) {
		return /^[가-힣]+$/.test(value);
	}, "한글음절만 가능(최대 4글자)");

	$j.validator.addMethod("engCheck", function(value, element) {
		return /^[a-zA-Z0-9]*$/.test(value);
	}, "영어, 숫자만 기술")
	$j.validator.addMethod("dupCheck", function(value, element) {
		return (($j("input[name=user_id]").data("check")) == 'checked' ? true : false);
	}, "중복체크 바람.")
	$j.validator.addMethod("phoneCheck", function(value, element) {
		return (($j("#user_phone1 > option:selected").val() != 'default') && ($j("#user_phone2").val().length) == 4 && ($j("#user_phone3").val().length == 4));
	}, "")
	$j.validator.addMethod("isSelected", function(value, element) {
		return (($j("#sel_user_phone1 > option:selected").val() != 'default'))
	}, "앞자리 선택,")
	$j.validator.addMethod("lengthCheck", function(value, element) {
		return (value.length == 4)
	}, "4자리 입력,")
});