
var ordersList = function(){
	
	return{
		init:function(){
			$("#create_time").datepicker();
		}
	}
}();

$(function(){
	ordersList.init();
})