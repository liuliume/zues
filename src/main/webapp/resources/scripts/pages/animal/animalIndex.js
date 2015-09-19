var animalIndex=function(){
	
	var createOrUpdate = function(){
		
		$("span[id^=errorInfo]").each(function(index,item){
			$(item).hide();
		});
		$("input[type=text]").each(function(index,item){
			$(item).removeAttr("style");
		})
		
		var animalsName = $("#animalsName").val();
		if(animalsName ==null || animalsName==''){
			$("#errorInfoName").text("宠物名称不能为空");
			$("#errorInfoName").css("display","block");
			$("#animalsName").css("border-color","red");
			return false;
		}
		
		if(animalsName.length>32){
			$("#errorInfoName").text("宠物名称长度不超过32");
			$("#errorInfoName").css("display","block");
			$("#animalsName").css("border-color","red");
			return false;
		}

        var typeId = $("#typeId").val();
        if(typeId==-1){
            $("#errorInfoType").css("display","block");
            $("#typeId").css("border-color","red");
            return false;
        }
		
		var orderType=$("#orderType").val();
		if(orderType==null || orderType==''){
			$("#error_orderType").css("display","block");
			return false;
		}



        var orderTypeOrderBy=$("#orderTypeOrderBy").val();
        if(orderTypeOrderBy ==null || orderTypeOrderBy==''){
            $("#error_orderTypeOrderBy").css("display","block");
            $("#orderTypeOrderBy").css("border-color","red");
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