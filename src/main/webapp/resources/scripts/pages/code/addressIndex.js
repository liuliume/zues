var AddressIndex = function() {

	var createOrUpdate = function() {
		urls = "/code/address/createOrUpdate";
        var data;
        if($("#name").val()==null || $("#name").val()==''){
            $("#errorInfoAddress").css("display", "block");
            $("#name").css("border-color", "red");
            return;
        }
        if($("#level").val() == '1'){
            data = "level="+$("#level").val()+"&name="+$("#name").val();
        } else if($("#level").val() == '2') {
            data = "level="+$("#level").val()+"&name="+$("#name").val()+"&parentId="+$("#parent_first_id").val();
            if($("#parent_first_id").val()==null || $("#parent_first_id").val()==''){
                $("#parent_address_first_error").css("display", "block");
                $("#parent_first_id").css("border-color", "red");
                return;
            }
        } else if($("#level").val() == '3') {
            data = "level="+$("#level").val()+"&name="+$("#name").val()+"&parentId="+$("#parent_second_id").val();
            if($("#parent_first_id").val()==null || $("#parent_first_id").val()==''){
                $("#parent_address_first_error").css("display", "block");
                $("#parent_first_id").css("border-color", "red");
                return;
            }
            if($("#parent_second_id").val()==null || $("#parent_second_id").val()==''){
                $("#parent_address_second_error").css("display", "block");
                $("#parent_second_id").css("border-color", "red");
                return;
            }
        }
        if($("#orderBy").val()==null || $("#orderBy").val()==''){
            $("#errorOrderBy").css("display", "block");
            $("#orderBy").css("border-color", "red");
            return;
        }else{
            data = data + "&orderBy=" + $("#orderBy").val();
        }
        if($("#address_id").val()!='' || $("#address_id").val()!=null){
            data = data + "&id=" + $("#address_id").val();
        }

		$.ajax({
			url:urls,
			type:"POST",
			data:data,
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
				window.location.href="/code/address/list";
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
			$(document).on("click", "#btnConfirm1", function(event){
				createOrUpdate();
				return false;
    		});

            $("#level").change(function(){
                if($("#level").val() == '1'){
                    $("#parent_first_div").css("display","none");
                    $("#parent_second_div").css("display","none");
                } else if($("#level").val() == '2') {
                    $("#parent_first_div").css("display","block");
                    $("#parent_second_div").css("display","none");
                } else if($("#level").val() == '3') {
                    $("#parent_first_div").css("display","block");
                    $("#parent_second_div").css("display","block");
                }
            });

            $("#parent_first_id").change(function(){
                if($("#level").val() == '3' && $("#parent_first_id").val()!=''){
                    $.ajax({
                        url:"/code/address/index_parent",
                        type:"GET",
                        data:'parent_id='+$("#parent_first_id").val(),
                        success:function(result) {
                            $("#parent_second_id").find("option").remove();
                            $("#parent_second_id").append("<option value='' selected>请选择</option>");
                            var obj = eval(result);
                            for(var p in obj) {
                                $("#parent_second_id").append("<option value='"+obj[p].id+"'>"+obj[p].name+"</option>");
                            }
                        },failure:function(result){
                            alert("操作失败",result.detail);
                        }
                    });
                }
            })
		}
	}
}();

$(function() {
	AddressIndex.init();
})