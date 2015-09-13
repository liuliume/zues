var typeIndex=function(){
	
	var createOrUpdate = function(){
		$("span[id^=errorInfo]").each(function(index,item){
			$(item).hide();
		});
		$("input[type=text]").each(function(index,item){
			$(item).removeAttr("style");
		})
		
		var typeName = $("#typeName").val();
		if(typeName ==null || typeName==""){
			$("#errorInfoName").text("宠物类型不能为空");
			$("#errorInfoName").css("display","block");
			$("#typeName").css("border-color","red");
			return false;
		}
		var expenseCoefficient = $("#expenseCoefficient").val();
		if(expenseCoefficient== null || expenseCoefficient==""){
			$("#errorInfoExpense").text("价格系数信息不能为空");
			$("#errorInfoExpense").css("display","block");
			$("#expenseCoefficient").css("border-color","red");
			return false;
		}
		if(isNaN(expenseCoefficient)){
			$("#errorInfoExpense").text("价格系数必须为数字");
			$("#errorInfoExpense").css("display","block");
			$("#expenseCoefficient").css("border-color","red");
			return false;
		}
		
		var urls = "/animal/createOrUpdateType";
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
				window.location.href="/animal/typeList";
//				window.location.reload();
			},failure:function(result){
				alert("操作失败",result.detail);
			}
		});
	}
	
	return{
		init:function(){
			$(document).on("click", "#btnConfirm", function(event){
				createOrUpdate();
				return false;
    		});
		}
	}
}();

$(function(){
	typeIndex.init();
})