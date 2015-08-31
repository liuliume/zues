var AddressIndex = function() {

	var createOrUpdate = function() {
		urls = "/account/createOrUpdate";
		
		var name=$("#uniqname").val();
		if(name==null || name==""){
			$("#errorInfoName").css("display","block");
			$("#name").css("border-color","red");
			return;
		}
		var mobile = $("#mobile").val();
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(mobile==null || mobile == ""){
			$("#errorInfoMobile").css("display","block");
			$("#mobile").css("border-color","red");
			return;
		}
		if(!reg.test(mobile)){
			$("#errorInfoMobile").text("请输入正确的手机号");
			$("#errorInfoMobile").css("display","block");
			$("#mobile").css("border-color","red");
			return;
		}
		
		
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
				window.location.href="/account/list";
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
			$(document).on("click", "#btnConfirm", function(event){
				createOrUpdate();
				return false;
    		});

            $("#level").change(function(){
                if($("#level").val() == '1'){
                    $("#parent_first_div").css("display","none");
                } else {
                    $("#parent_first_div").css("display","block");
                    $.ajax({
                        url:"/code/address/index_parent",
                        type:"POST",
                        data:$("#level").val(),
                        success:function(result) {
                           alert(result);
                        },failure:function(result){
                            alert("操作失败",result.detail);
                        }
                    });
                }
            });

		}
	}
}();

$(function() {
	AddressIndex.init();
})