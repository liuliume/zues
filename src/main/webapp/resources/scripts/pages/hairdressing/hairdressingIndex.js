var Course = function() {

	var createOrUpdate = function() {
		urls = "/hairdressing/createOrUpdate";

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