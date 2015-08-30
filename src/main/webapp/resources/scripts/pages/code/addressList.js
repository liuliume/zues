var Address=function(){
	
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
    		
    		$("#createAddress").click(function(){
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
    				confirm("Delete Account","Please confirm if delete all the selected Account?",batchDelete);
    			}else{
    				alert("Delete Account","Please choose the Account to be deleted.");
    			}
    		});
    		
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
//重写alert,界面关闭不需要执行某些操作(刷新界面)
function alert(title,content){
	$("#alertModalTitle").html(title);
	$("#alertModalContent").html(content);
	$("#alertModal").modal();
}
//重写alert，界面关闭需要执行默写操作(刷新界面)
function alert(title,content,functionName){
	$("#alertModalTitle").html(title);
	$("#alertModalContent").html(content);
	$("#alertOkBtn").bind("click",functionName);
	$("#alertModal").modal();
}
//重写confirm，functionName是确定按钮绑定的方法
function confirm(title,content,functionName){
	$("#confirmModalTitle").html(title);
	$("#confirmModalContent").html(content);
	$("#confirmOkBtn").bind("click",functionName);
	$("#confirmModal").modal();
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