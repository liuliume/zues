var FosterIndex = function(){
	
	var createOrUpdate = function(){
		console.log("111");
		
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
				window.location.href="/orders/list";
//				window.location.reload();
			},failure:function(result){
				alert("操作失败",result.detail);
			}
		});
	}
	
	return{
		init:function(){
			handleSelect2();
			$("#startDate").datepicker();
			$("#endDate").datepicker();
			
			$("#btnConfirm").click(function(){
				createOrUpdate();
				return false;
			})
		}
	}
}();

$(function(){
	FosterIndex.init();
})