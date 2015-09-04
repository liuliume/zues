var orderDetail = function(){
	
	var payOrder = function(){
		var orderId = $("#orderId").val();
		$.ajax({
			url:"/orders/payOrder",
			type:"POST",
			data:{"orderId":orderId},
			dataType:"json",
			success:function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:result.detail});
				}else{
					$.cookie('action-message',{action:"error",message:result.detail});
				}
				window.location.reload();
			},
			failure:function(result){
				alert("系统错误","系统错误,请重试");
			}
		});
	}
	
	return {
		init:function(){
			$("#btnPay").click(function(){
				var orderId = $("#orderId").val();
				var paymentStatus = $("#paymentStatus").val();
				
				if(orderId==null){
					alert("系统错误","订单ID不存在");
					return false;
				}
				if(paymentStatus==null || paymentStatus!=0){
					alert("系统错误","订单不在可支付状态,请确认");
					return false;
				}
				confirm("付款确认","确认订单  "+name+"?",payOrder);
			});
		}
	}
}();

$(function(){
	orderDetail.init();
})