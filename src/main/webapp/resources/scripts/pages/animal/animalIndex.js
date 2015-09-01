var animalIndex=function(){
	
	var createOrUpdate = function(){
		var animalsName = $("#animalsName").val();
		if(animalsName ==null || animalsName==""){
			$("#errorInfoName").text("宠物名称不能为空");
			$("#errorInfoName").css("display","block");
			$("#typeName").css("border-color","red");
			return false;
		}
		
		if(animalsName.length>32){
			$("#errorInfoName").text("宠物名称长度不超过32");
			$("#errorInfoName").css("display","block");
			$("#typeName").css("border-color","red");
			return false;
		}
		
		var type=$("#typeId").val();
		if(type==-1){
			$("#errorInfoType").text("请选择一个宠物类型");
			$("#errorInfoType").css("display","block");
			return false;
		}
		
		var urls = "/animal/createOrUpdateAnimals";
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
				window.location.href="/animal/listAnimals";
//				window.location.reload();
			},failure:function(result){
				alert("操作失败",result.detail);
			}
		});
	}
	
	return{
		init:function(){
			
			handleSelect2();
			
			$(document).on("click", "#btnConfirm", function(event){
				createOrUpdate();
				return false;
    		});
		}
	}
}();

$(function(){
	animalIndex.init();
})