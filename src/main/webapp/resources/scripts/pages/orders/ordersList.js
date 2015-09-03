
var ordersList = function(){
	
	return{
		init:function(){
			
			handleSelect2();
			$("#create_time").datepicker();
			
			$("#create").click(function(){
				$("#selectModal").modal();
			});
			
			$("#btnConfirm").click(function(){
				var type_id = $("#typeId").val();
				console.info(type_id);
				if(type_id==-1){
					alert("请选择订单 类型","新建订单必须选择订单类型");
				}
				else
					window.location.href="/orders/index?orderType="+type_id;
			})
		}
	}
}();


$(function(){
	ordersList.init();
})