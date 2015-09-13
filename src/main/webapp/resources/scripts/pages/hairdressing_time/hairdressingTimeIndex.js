var HairdressingTime = function() {

	var createOrUpdate = function() {
		urls = "/hairdressing_time/createOrUpdate";

        if ($("#servicePersionNum").val() == null || $("#servicePersionNum").val() == "") {
            $("#servicePersionNum_error").css("display", "block");
            $("#servicePersionNum").css("border-color", "red");
            return;
        }

        if(isNaN($("#servicePersionNum").val())){
            $("#servicePersionNum_error").text("必须为数字");
            $("#servicePersionNum_error").css("display","block");
            $("#servicePersionNum").css("border-color","red");
            return false;
        }

        var reg= /^\d+$/;
        if(!reg.test($("#servicePersionNum").val())){
            $("#servicePersionNum_error").css("display", "block");
            $("#servicePersionNum_error").text("房间数量必须为正整数")
            $("#servicePersionNum").css("border-color", "red");
            return;
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
				window.location.href="/hairdressing_time/list";
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
    HairdressingTime.init();
})