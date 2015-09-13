var Course = function() {

	var createOrUpdate = function() {
		urls = "/course/createOrUpdate";


        if ($("#course_name").val() == null || $("#course_name").val() == "") {
            $("#course_name_error").css("display", "block");
            $("#course_name").css("border-color", "red");
            return;
        }
        if ($("#course_describe").val() == null || $("#course_describe").val() == "") {
            $("#errorCourseDescribe").css("display", "block");
            $("#course_describe").css("border-color", "red");
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
				window.location.href="/course/list";
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