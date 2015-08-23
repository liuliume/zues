var liuliumeTest = function(){
	
	return{
		init:function(){
			$("#btn").click(function(){
				var name=$("#name").val();
				var passwd=$("#passwd").val();
				$.ajax({
					url:"/test/add",
					type:"POST",
					data:$("#testForm").serialize(),
					dataType:"json",
					success:function(result){
						if(result.success)
							alert(result.detail);
						else
							alert(result.detail);
					}
				});
				return false;
			});
		}
	};
}();

$(function(){
	liuliumeTest.init();
});