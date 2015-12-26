<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<html>
<head>
<title>支付</title>
<script type="text/javascript" src="/resources/third-party/metronic/assets/plugins/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
		
		var jsApiCall = function(){
			alert("jsAPICall begin");
			var json = ${result};
			alert(json);
			WeixinJSBridge.invoke(
					'getBrandWCPayRequest',
					json,
					function(res){
						WeixinJSBridge.log(res.err_msg);
						//alert(res.err_code+res.err_desc+res.err_msg);
						if(res.err_msg=='get_brand_wcpay_request:ok'){
	 		    	    	   alert('恭喜您，支付成功!');
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
				alert(0);
        		if(document.addEventListener){
        			alert("1");
        			document.addEventListener('WeixinJSBridgeReady',jsApiCall , false);
        		}else if (document.attachEvent){
        			alert(2);
			        document.attachEvent('WeixinJSBridgeReady', jsApiCall); 
			        document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
			    }
        	}else{
        		alert(3)
        		jsApiCall();
        	}
		}
	</script>
</head>
<body>
	${result }
	
	<input type="button" value="确认支付" onclick="pay">

</body>
</html>