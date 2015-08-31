var ZuesAccount=function(){
	
	//批量删除Account
	var batchDelete = function(){
		var urls=$("#setOptionUrl").val()+"batchDelete";
		var account_ids = $("#accountId_delete").val();
		$.ajax({
			type: "POST",
			url: urls,
			data: {"accountIds":account_ids},
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
	
	var deletePromotion = function(){
//		alert("");
		var accountId = $("#accountId_delete").val();
		$.ajax({
			type: "POST",
			url: "/account/batchDelete",
			data: {"accountIds":accountId},
			dataType: "json",
			success: function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:result.detail});
				}else{
					$.cookie('action-message',{action:"error",message:result.detail});
				}
				window.location.reload();
			 }
		})
	}
	
	return{
		init:function(){
			//init page param
			//单选框，全选或者全部不选的控制
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
    		
    		$("#createAccount").click(function(){
    			var url =  $("#setOptionUrl").val() + "index";
    			window.location.href=url;  
    		});
    		
    		$("#deleteAccountBatch").click(function(){
    			var accountIds='';
    			$("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
    				if($(item).attr("checked")){
    					accountIds+=$(item).val()+",";
    				}
				});
    			if(accountIds!=''){
    				accountIds=accountIds.substring(0,accountIds.length-1);
    				$("#accountId_delete").val(accountIds);
    				confirm("删除用户","确认删除这些用户?",batchDelete);
    			}else{
    				alert("删除用户","请选择需要删除的用户.");
    			}
    		});
    		
    		$("a[name=deleteAccount]").click(function(){
    			
    			$("#accountId_delete").val($(this).attr("accountId"));
    			
    			var name=$($(this).parent().parent().find("td")[1]).text();
    			name=name.replace(/(^\s*)|(\s*$)/g,'');
    			confirm("删除用户","确认删除这些用户  "+name+"?",deletePromotion);
    		});
    		
    		$("a[name=editAccount]").click(function(){
    			var url = $("#setOptionUrl").val() + "index" + "?" + "account_id=" + $(this).attr("accountId");
    			window.location.href=url;  
    		})
    		
		},
		search:function(){
			$("#form_search").submit();
		}
	};
}();

//界面刷新
function refresh(){
	window.location.reload();
}



//功能: 1)去除字符串前后所有空格 
//2)去除字符串中所有空格(包括中间空格,需要设置第2个参数为:g) 
function Trim(str,is_global) 
{ 
	var result; 
	result = str.replace(/(^\s+)|(\s+$)/g,""); 
	if(is_global.toLowerCase()=="g") 
	result = result.replace(/\s/g,""); 
	return result; 
} 


$(function(){
	ZuesAccount.init();
})