var Room = function() {

	var createOrUpdate = function() {
		
		$("span[id$=_error]").each(function(index,item){
			$(item).hide();
		});
		$("input[type=text]").each(function(index,item){
			$(item).removeAttr("style");
		})
		
		var roomName = $("#name").val();
		if(roomName==null || roomName==""){
			$("#roomName_error").css("display", "block");
			$("#name").css("border-color", "red");
			return;
		}
		var cost = $("#cost").val();
		if(cost==null||cost==""){
			$("#cost_error").css("display", "block");
			$("#cost_error").text("请输入费用")
			$("#cost").css("border-color", "red");
			return;
		}
		if(isNaN(cost)){
			$("#cost_error").css("display", "block");
			$("#cost_error").text("费用必须为数字类型")
			$("#cost").css("border-color", "red");
			return;
		}
		
		var weixin_discount = $("#weixin_discount").val();
		if(weixin_discount==null||weixin_discount==""){
			$("#weixin_discount_error").css("display", "block");
			$("#weixin_discount_error").text("请输入微信折扣")
			$("#weixin_discount").css("border-color", "red");
			return;
		}
		if(isNaN(weixin_discount)){
			$("#weixin_discount_error").css("display", "block");
			$("#weixin_discount_error").text("微信折扣必须为数字类型")
			$("#weixin_discount").css("border-color", "red");
			return;
		}
		if(parseFloat(weixin_discount)>1){
			$("#weixin_discount_error").css("display", "block");
			$("#weixin_discount_error").text("折扣必须小于1")
			$("#weixin_discount").css("border-color", "red");
			return;
		}
		
		var discount_30 = $("#discount_30").val();
		if(discount_30==null||discount_30==""){
			$("#discount_30_error").css("display", "block");
			$("#discount_30_error").text("请输入30天折扣")
			$("#discount_30").css("border-color", "red");
			return;
		}
		if(isNaN(discount_30)){
			$("#discount_30_error").css("display", "block");
			$("#discount_30_error").text("折扣必须为数字类型")
			$("#discount_30").css("border-color", "red");
			return;
		}
		if(parseFloat(discount_30)>1){
			$("#discount_30_error").css("display", "block");
			$("#discount_30_error").text("折扣必须小于1")
			$("#discount_30").css("border-color", "red");
			return;
		}
		
		var discount_90 = $("#discount_90").val();
		if(discount_90==null||discount_90==""){
			$("#discount_90_error").css("display", "block");
			$("#discount_90_error").text("请输入60天折扣")
			$("#discount_90").css("border-color", "red");
			return;
		}
		if(isNaN(discount_90)){
			$("#discount_90_error").css("display", "block");
			$("#discount_90_error").text("折扣必须为数字类型")
			$("#discount_90").css("border-color", "red");
			return;
		}
		if(parseFloat(discount_90)>1){
			$("#discount_90_error").css("display", "block");
			$("#discount_90_error").text("折扣必须小于1")
			$("#discount_90").css("border-color", "red");
			return;
		}
		
		var discount_180 = $("#discount_180").val();
		if(discount_180==null||discount_180==""){
			$("#discount_180_error").css("display", "block");
			$("#discount_180_error").text("请输入60天折扣")
			$("#discount_180").css("border-color", "red");
			return;
		}
		if(isNaN(discount_180)){
			$("#discount_180_error").css("display", "block");
			$("#discount_180_error").text("折扣必须为数字类型")
			$("#discount_180").css("border-color", "red");
			return;
		}
		if(parseFloat(discount_180)>1){
			$("#discount_180_error").css("display", "block");
			$("#discount_180_error").text("折扣必须小于1")
			$("#discount_180").css("border-color", "red");
			return;
		}
		
		var room_num = $("#room_num").val();
		var reg= /^\d+$/;
		if(room_num==null||room_num==""){
			$("#room_num_error").css("display", "block");
			$("#room_num_error").text("请输入房间数量")
			$("#room_num").css("border-color", "red");
			return;
		}
		if(!reg.test(room_num)){
			$("#room_num_error").css("display", "block");
			$("#room_num_error").text("房间数量必须为正整数")
			$("#room_num").css("border-color", "red");
			return;
		}
		
		urls = "/room/createOrUpdate";
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
				window.location.href="/room/list";
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
	Room.init();
})