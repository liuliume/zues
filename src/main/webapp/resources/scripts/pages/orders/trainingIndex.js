var trainIndex = function(){
	
	var createOrUpdate = function(){
		console.log("111");
		
		var animalsId = $("#animalsId").val();
		if(animalsId == null || animalsId < 0){
			$("#errorInfoAnimal").css("display","block");
			return;
		}
		
		var startDate = $("#startDate").val();
		if(startDate == null ||startDate == ""){
			$("#errorInfoStartDate").css("display","block");
			$("#name").css("border-color","red");
			return;
		}
		
		var endDate = $("#endDate").val();
		if(endDate == null ||endDate == ""){
			$("#errorInfoEndDate").css("display","block");
			$("#name").css("border-color","red");
			return;
		}
		
		var start = new Date(startDate);
		var end = new Date(endDate);
		if(end<start){
			$("#errorInfoEndDate").css("display","block");
			$("#errorInfoEndDate").text("结束日期不能小于开始日期");
			$("#name").css("border-color","red");
			return;
		}
		
		var courseId = $("#courseId").val();
		if(courseId == null || courseId <=0){
			$("#errorInfoCourse").css("display","block");
			return;
		}
		
		var accountId = $("#accountId").val();
		if(accountId == null || accountId <= 0){
			$("#errorInfoAccount").css("display","block");
			return;
		}
		
		var urls = "/orders/createOrUpdate";
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
			});
		}
	}
}();

$(function(){
	trainIndex.init();
})