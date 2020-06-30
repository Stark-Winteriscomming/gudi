const ajaxObj = {
	getOptions: function(url, successFn, domTarget, optionName, domTargetTagName) {
		$j.ajax({
			url: url + "/" + optionName,
			dataType: "json",
			type: "GET",
			success: function(data, textStatus, jqXHR) {
				//					console.log(domTarget)
				//					domTarget.html("");
				//					domTarget.append("<option value='default' selected>선택</option>");
				console.log(`dom length: ${domTarget.length}`)
				if (domTargetTagName == "select") {
					for (let i = 0; i < domTarget.length; i++) {
						for (let j = 0; j < data.length; j++) {
							let element = document.createElement("option");
							element.setAttribute("value", data[i].code_id);
							element.appendChild(document.createTextNode(data[j].code_name));
							domTarget[i].appendChild(element);
						}
					}
				}
				else if (domTargetTagName == "div") {
					for (i = 0; i < data.length; i++) {
						domTarget.append(
							"<input type='checkbox' name='option' value=" + data[i].code_id + " />" +
							"<label for=" + data[i].code_id + ">" +
							data[i].code_name +
							"</label>"
						);
					}

				} else {
					console.log("improper value for domTargetName")
				}

			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("실패");
			}
		});
	},

	updateData: function(url, param, type, oper) {
		$j.ajax({
			url: url,
			dataType: "json",
			type: type,
			data: param,
			success: function(data, textStatus, jqXHR) {
				let msg;
				if (oper == "rm") msg = "삭제완료";
				else if (oper == "update") msg = "수정완료";
				else if (oper == "write") msg = "작성완료";
				(data.success == 'Y') ? alert(msg) : alert("실패");
				location.href = data.href;
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("실패");
			}
		});
	}
}