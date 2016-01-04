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
					window.location.reload();
				}else{
					alert("订单收款错误",result.detail);
				}
			},
			failure:function(result){
				alert("系统错误","系统错误,请重试");
			}
		});
	};
	
	var setInvalid = function(){
		var orderId = $("#orderId").val();
		$.ajax({
			url:"/orders/invalidOrder",
			type:"POST",
			data:{"orderId":orderId},
			dataType:"json",
			success:function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:result.detail});
					window.location.reload();
				}else{
					alert("订单收款错误",result.detail,function(){
						window.location.reload();
					});
				}
//				window.location.reload();
			},
			failure:function(result){
				alert("系统错误","系统错误,请重试");
			}
		});
	};
	
	var transferOrder = function(){
		var orderId = $("#orderId").val();
		$.ajax({
			url:"/orders/transferOrder",
			type:"POST",
			data:{"orderId":orderId},
			dataType:"json",
			success:function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:result.detail});
					window.location.reload();
				}else{
					alert("订单转发错误",result.detail,function(){
						window.location.reload();
					});
				}
			},
			failure:function(result){
				alert("系统错误","系统错误,请重试");
			}
		});
	};
	
	var doneOrder = function(){
		var orderId = $("#orderId").val();
		$.ajax({
			url:"/orders/completeOrder",
			type:"POST",
			data:{"orderId":orderId},
			dataType:"json",
			success:function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:result.detail});
					window.location.reload();
				}else{
					alert("订单转发错误",result.detail,function(){
						window.location.reload();
					});
				}
			},
			failure:function(result){
				alert("系统错误","系统错误,请重试");
			}
		});
	}
	
	var refundOrder = function(){
		var orderId = $("#orderId").val();
		$.ajax({
			url:"/orders/refundOrder",
			type:"POST",
			data:{"orderId":orderId},
			dataType:"json",
			success:function(result){
				$.cookie.json = true;
				if(result.success){
					$.cookie('action-message',{action:"success",message:result.detail});
					window.location.reload();
				}else{
					alert("订单退款错误",result.detail,function(){
						window.location.reload();
					});
				}
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
				confirm("付款确认","确认订单  "+orderId+"收款?",payOrder);
			});
			
			$("#btnInvalid").click(function(){
				var orderId = $("#orderId").val();
				var orderStatus = $("#orderStatus").val();
				if(orderId == null){
					alert("系统错误","订单ID不存在");
					return false;
				}
				if(orderStatus==5){
					alert("订单置无效","订单已退款,不能置无效");
					return false;
				}
				if(orderStatus>0){
					alert("订单置无效","订单已服务,不能置无效");
					return false;
				}
				confirm("付款确认","确认订单  "+orderId+"置无效?",setInvalid);
				return false;
			});
			
			$("#BtnTransfer").click(function(){
				var orderId = $("#orderId").val();
				var orderStatus = $("#orderStatus").val();
				if(orderId == null){
					alert("系统错误","订单ID不存在");
					return false;
				}
				if(orderStatus==5){
					alert("订单置无效","订单已退款,不能置无效");
					return false;
				}
				if(orderStatus!=0){
					alert("订单转发","订单已服务,不能转发");
					return false;
				}
				confirm("转发确认","确认转发订单  "+orderId+"?",transferOrder);
				return false;
			});
			
			$("#done").click(function(){
				var orderId = $("#orderId").val();
				if(orderId == null){
					alert("系统错误","订单ID不存在");
					return false;
				}
				if(orderStatus==5){
					alert("订单置无效","订单已退款,不能置无效");
					return false;
				}
				confirm("转发确认","确认订单完成  "+orderId+"?",doneOrder);
				return false;
			});
			
			$("#btnRefund").click(function(){
				var orderId = $("#orderId").val();
				var orderStatus = $("#orderStatus").val();
				if(orderId == null){
					alert("系统错误","订单ID不存在");
					return false;
				}
				if(orderStatus>0){
					alert("订单退款","订单已经开始服务,不能退款");
				}
				confirm("退款确认","确认订单 "+orderId+"退款?",refundOrder);
			})
		}
	}
}();

$(function(){
	orderDetail.init();
})