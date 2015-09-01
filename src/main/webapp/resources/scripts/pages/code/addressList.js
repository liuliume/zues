var Address=function(){
	
	//批量删除Account
	var batchDelete = function(){
		var urls=$("#setOptionUrl").val()+"batchDelete";
		var addressIds = $("#accountId_delete").val();
		$.ajax({
			type: "POST",
			url: urls,
			data: {"addressIds":addressIds},
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

    var deletePromotion = function(){
//		alert("");
        var addressId = $("#accountId_delete").val();
        $.ajax({
            type: "POST",
            url: "/code/address/batchDelete",
            data: {"addressIds":addressId},
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
    				$("table#fromAddressTable tbody input[type='checkbox']").uniform().each(function(index, item){
    					$.uniform.update($(item).attr("checked", true));
    				});
    			}else{
    				$("table#fromAddressTable tbody input[type='checkbox']").uniform().each(function(index, item){
    					$.uniform.update($(item).attr("checked", false));
    				});
    			}
    		});//全选结束
    		
    		$("#createAddress").click(function(){
    			var url =  $("#setOptionUrl").val() + "index";
    			window.location.href=url;  
    		});
    		
    		$("#deleteAddressBatch").click(function(){
    			var addressIds='';
    			$("table#fromAddressTable tbody input[type='checkbox']").uniform().each(function(index, item){
    				if($(item).attr("checked")){
                        addressIds+=$(item).val()+",";
    				}
				});
    			if(addressIds!=''){
                    addressIds=addressIds.substring(0,addressIds.length-1);
    				$("#accountId_delete").val(addressIds);
    				confirm("删除地址","请确认是否删除这些地址?",batchDelete);
    			}else{
    				alert("删除地址","请选择需要删除的地址");
    			}
    		});

            $("a[name=deleteAddress]").click(function(){

                $("#accountId_delete").val($(this).attr("address_id"));

                var name=$($(this).parent().parent().find("td")[2]).text();
                name=name.replace(/(^\s*)|(\s*$)/g,'');
                confirm("删除地址","确认删除这些地址  "+ name +"?",deletePromotion);
            });

            $("a[name=editAddress]").click(function(){
                var url = $("#setOptionUrl").val() + "index" + "?" + "address_id=" + $(this).attr("address_id");
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
	Address.init();
})