const ajaxObj = {
		getOptions : function(url, successFn, domTarget, optionName, domTargetTagName){
			$j.ajax({	
				url : url + "/" + optionName,
				dataType : "json",
				type : "GET",
				success : function(data, textStatus, jqXHR) {
					console.log(domTargetTagName)
					if(domTargetTagName == "select"){
						for (i = 0; i < data.length; i++) {
							domTarget.append("<option value=" + "'" + data[i].code_id + "'>" + data[i].code_name +"</option>" );
						}
					}
					else if(domTargetTagName == "div"){
						for (i = 0; i < data.length; i++){
							domTarget.append(
                            "<input type='checkbox' name='option' value=" + data[i].code_id + " />" +
                            "<label for=" + data[i].code_id + ">" +
                            data[i].code_name +
                            "</label>"
                            );
						}
						
					}else{
						console.log("improper value for domTargetName")
					}
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("실패");
				}
			});
		},
		
		updateData : function(url, param, type){
			console.log(url)
			$j.ajax({
				url : url,
				dataType : "json",
				type : type,
				data : param,
				success : function(data, textStatus, jqXHR) {
					(data.success == 'Y') ? alert("작성완료") : alert("작성실패");
					location.href = data.href;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("실패");
				}
			});
		}
}

