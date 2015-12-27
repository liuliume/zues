<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<html>
<head>
<title>支付</title>
<script type="text/javascript" src="/resources/third-party/metronic/assets/plugins/jquery-1.10.2.min.js"></script>

</head>
<body>
	${result }
	
	<script type="text/javascript">
		var orderId = "${orderId}";
		
		var compeleOrder = function(){
			$.ajax({
	    		   url:'/orders/completeOrderPaymentState',
	    		   type:'POST',
	    		   data:{"order_id":orderId},
	    		   success:function(result){
	    			   if(result.success){
	    				   window.location.href="http://www.liuliume.com/resources/front_views/order-confirm.html?ordersId=" + orderId;
	    			   }else{
	    				   alert('更新支付状态失败');
	    			   }
	    		   },failure:function(){
	    			   alert('更新支付状态失败');
	    		   }
	    	   })
		};
		
		var jsApiCall = function(){
			var json = ${result};
			WeixinJSBridge.invoke(
					'getBrandWCPayRequest',
					json,
					function(res){
						WeixinJSBridge.log(res.err_msg);
						//alert(res.err_code+res.err_desc+res.err_msg);
						if(res.err_msg=='get_brand_wcpay_request:ok'){
	 		    	    	   alert('恭喜您，支付成功!');
	 		    	    	  compeleOrder();
	 		    	    	   //更新订单支付状态
	 		    	    	   /* $.ajax({
	 		    	    		   url:'/orders/completeOrderPaymentState',
	 		    	    		   type:'POST',
	 		    	    		   data:order_id,
	 		    	    		   success:function(result){
	 		    	    			   if(result.success){
	 		    	    				   window.location.href="order-confirm.html?ordersId=" + order_id;
	 		    	    			   }else{
	 		    	    				   alert('更新支付状态失败');
	 		    	    			   }
	 		    	    		   },failure:function(){
	 		    	    			   alert('更新支付状态失败');
	 		    	    		   }
	 		    	    	   }) */
	 		    	       }else{
	 		    	    	   alert('支付失败');
	 		    	       }
					}
				);
		}
		
		
		function pay(){
			if(typeof WeixinJSBridge == "undefined"){
        		if(document.addEventListener){
        			document.addEventListener('WeixinJSBridgeReady',jsApiCall , false);
        		}else if (document.attachEvent){
			        document.attachEvent('WeixinJSBridgeReady', jsApiCall); 
			        document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
			    }
        	}else{
        		jsApiCall();
        	}
		}
		
		$(function(){
			pay();
		})
	</script>

</body>
</html>