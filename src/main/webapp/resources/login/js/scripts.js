
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
	var ctx = $("#ctx").val();
    $.backstretch(ctx+"/resources/login/img/backgrounds/1.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    	$("#error-msg").text("");
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    	login();
    	
    	e.preventDefault();
    });
    
    var login = function(){
    	var user = $("#form-username").val();
    	var passwd = $("#form-password").val();
    	$.ajax({
    		url:'/login',
    		data:{"name":user,"passwd":passwd},
    		type : "POST",
    		success:function(result){
    			if(result.success){
    				window.location.href='/index';
    			}else{
    				$("#error-msg").text("用户名或密码错误");
    			}
    		},
    		failure:function(result){
    			$("#error-msg").text("登陆失败");
    		}
    	});
    	return false;
    }
});
