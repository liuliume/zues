var Course = function() {

	var createOrUpdate = function() {
		urls = "/hairdressing/createOrUpdate";

        if ($("#hairdressing_name").val() == null || $("#hairdressing_name").val() == "") {
            $("#hairdressing_name_error").css("display", "block");
            $("#hairdressing_name").css("border-color", "red");
            return;
        }

        if ($("#hairdressingDescribe").val() == null || $("#hairdressingDescribe").val() == "") {
            $("#hairdressingDescribe_error").css("display", "block");
            $("#hairdressingDescribe").css("border-color", "red");
            return;
        }

        if ($("#expense").val() == null || $("#expense").val() == "") {
            $("#expense_error").css("display", "block");
            $("#expense").css("border-color", "red");
            return;
        }
        if(isNaN($("#expense").val())){
            $("#expense_error").text("价格系数必须为数字");
            $("#expense_error").css("display","block");
            $("#expense").css("border-color","red");
            return false;
        }

		$.ajax({
			url:urls,
			type:"POST",
			data:$("#IndexForm").serialize(),
			success:function(result) {
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
				window.location.href="/hairdressing/list";
//				window.location.reload();
			},failure:function(result){
				alert("操作失败",result.detail);
			}
		});
	}

	return {
		init : function() {

//			$("#btnConfirm").click(function(){
//				createOrUpdate();
//			});
			$(document).on("click", "#btnConfirm1", function(event){
				createOrUpdate();
				return false;
    		});

		}
	}
}();

$(function() {
	Course.init();
})