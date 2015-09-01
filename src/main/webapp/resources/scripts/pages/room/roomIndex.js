var AddressIndex = function() {

	var createOrUpdate = function() {
		urls = "/code/address/createOrUpdate";
        var data;
        if($("#level").val() == '1'){
            data = "level="+$("#level").val()+"&name="+$("#name").val();
        } else if($("#level").val() == '2') {
            data = "level="+$("#level").val()+"&name="+$("#name").val()+"&parentId="+$("#parent_first_id").val();
        } else if($("#level").val() == '3') {
            data = "level="+$("#level").val()+"&name="+$("#name").val()+"&parentId="+$("#parent_second_id").val();
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

		}
	}
}();

$(function() {
	AddressIndex.init();
})