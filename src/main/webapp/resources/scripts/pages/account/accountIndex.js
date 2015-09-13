var ZuesAccountIndex = function() {

	var createOrUpdate = function() {
		urls = "/account/createOrUpdate";
		
		$("span[id^=errorInfo]").each(function(index,item){
			$(item).hide();
		});
		$("input[type=text]").each(function(index,item){
			$(item).removeAttr("style");
		})

		var name = $("#uniqname").val();
		if (name == null || name == "") {
			$("#errorInfoName").css("display", "block");
			$("#name").css("border-color", "red");
			return;
		}
		
		var gender = $("#gender").val();
		if(gender == -1){
			$("#errorInfoGender").css("display", "block");
			$("#gender").css("border-color", "red");
			return;
		}
		
		var mobile = $("#mobile").val();
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if (mobile == null || mobile == "") {
			$("#errorInfoMobile").css("display", "block");
			$("#mobile").css("border-color", "red");
			return;
		}
		if (!reg.test(mobile)) {
			$("#errorInfoMobile").text("请输入正确的手机号");
			$("#errorInfoMobile").css("display", "block");
			$("#mobile").css("border-color", "red");
			return;
		}
		
		var email = $("#email").val();
		reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (email != null && email != "") {
			if (!reg.test(email)) {
				$("#errorInfoEmail").text("请输入正确的邮箱");
				$("#errorInfoEmail").css("display", "block");
				$("#email").css("border-color", "red");
				return;
			}
		}

		$.ajax({
			url : urls,
			type : "POST",
			data : $("#IndexForm").serialize(),
			success : function(result) {
				$.cookie.json = true;
				if (result.success) {
					$.cookie('action-message', {
						action : "success",
						message : result.detail
					});
				} else {
					$.cookie('action-message', {
						action : "error",
						message : result.detail
					});
				}
				window.location.href = "/account/list";
				// window.location.reload();
			},
			failure : function(result) {
				alert("操作失败", result.detail);
			}
		});
	}

	var provinceChanged = function() {
		var province_id = $("#province_id").val();
		$.ajax({
			url : "/code/address/index_parent",
			type : "GET",
			data : 'parent_id=' + province_id,
			success : function(result) {
				$("#city_id").empty();
				$("#area_id").empty();
				$("#city_id").append(
						"<option value='-1' selected=true>ALL</option>");
				$("#area_id").append(
				"<option value='-1' selected=true>ALL</option>");
				var obj = eval(result);
				for ( var p in obj) {
					$("#city_id").append(
							"<option value='" + obj[p].id + "'>" + obj[p].name
									+ "</option>");
				}
				if (jQuery().select2) {
					$("#city_id").select2({
						placeholder : "Select",
						allowClear : true
					});
					$("#area_id").select2({
						placeholder : "Select",
						allowClear : true
					});
				}
			},
			failure : function(result) {
				alert("操作失败", result.detail);
			}
		})
	};

	var cityChanged = function() {
		var city_id = $("#city_id").val();
		$.ajax({
			url : "/code/address/index_parent",
			type : "GET",
			data : 'parent_id=' + city_id,
			success : function(result) {
				$("#area_id").empty();
				$("#area_id").append(
						"<option value='-1' selected=true>ALL</option>");
				var obj = eval(result);
				for ( var p in obj) {
					$("#area_id").append(
							"<option value='" + obj[p].id + "'>" + obj[p].name
									+ "</option>");
				}
				if (jQuery().select2) {
					$("#area_id").select2({
						placeholder : "Select",
						allowClear : true
					});
				}
			},
			failure : function(result) {
				alert("操作失败", result.detail);
			}
		})
	}

	return {
		init : function() {

			handleSelect2();

			$(document).on("click", "#btnConfirm", function(event) {
				createOrUpdate();
				return false;
			});

			$("#province_id").change(function() {
				provinceChanged();
			});

			$("#city_id").change(function() {
				cityChanged();
			});

		}
	}
}();

$(function() {
	ZuesAccountIndex.init();
})