var animalList=function(){
	
	var deletePromotion = function(){
		var urls=$("#setOptionUrl").val()+"batchDeleteAnimas";
		var ids = $("#animals_delete").val();
		$.ajax({
			type: "POST",
			url: urls,
			data: {"ids":ids},
			dataType: "json",
			success: function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:"操作成功"});
				}else{
					$.cookie('action-message',{action:"error",message:"操作失败"});
				}
				window.location.reload();
			}
		});
	};
	
	return{
		init:function(){
			//init page param
			$("#selectAll").change(function(){
    			if($(this).attr("checked")){
    				$("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
    					$.uniform.update($(item).attr("checked", true));
    				});
    			}else{
    				$("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
    					$.uniform.update($(item).attr("checked", false));
    				});
    			}
    		});//全选结束
			
			$("#create").click(function(){
				var url =  $("#setOptionUrl").val() + "animalsIndex";
    			window.location.href=url;  
			});
			
			$("#deleteBatch").click(function(){
				var ids='';
    			$("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
    				if($(item).attr("checked")){
    					ids+=$(item).val()+",";
    				}
				});
    			if(ids!=''){
    				ids=ids.substring(0,ids.length-1);
    				$("#animals_delete").val(ids);
    				confirm("删除宠物类型", "确认删除宠物类型?",deletePromotion);
    			}else{
    				alert("删除宠物类型","请选择需要删除的宠物类型.");
    			}
			});
			
			$("a[name=delete]").click(function(){
    			
    			$("#animals_delete").val($(this).attr("id"));
    			
    			var name=$($(this).parent().parent().find("td")[1]).text();
    			name=name.replace(/(^\s*)|(\s*$)/g,'');
    			confirm("删除宠物", "确认删除宠物:"+name+"?", deletePromotion);
    		});
			
			$("a[name=edit]").click(function(){
				var id=$(this).attr("id");
    			var url = $("#setOptionUrl").val() + "animalsIndex?id=" + $(this).attr("id");
    			window.location.href=url;  
    		})
		}
	};
}();

$(function(){
	animalList.init();
})