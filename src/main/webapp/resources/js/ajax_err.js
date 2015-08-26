(function($){
    //备份jquery的ajax方法
    var _ajax=$.ajax;

    //重写jquery的ajax方法
    $.ajax=function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
        }
        if(opt.error){
            fn.error=opt.error;
        }


        //扩展增强处理
        var _opt = $.extend(opt,{
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //错误方法增强处理
                if (XMLHttpRequest.responseText.indexOf('<title>登录</title>') > -1) {
                    alert("Session已过期，请重新登录。");
                    location.href = "${ctx}";
                }

                fn.error(XMLHttpRequest, textStatus, errorThrown);
            }
        });
        _ajax(_opt);
    };
})(jQuery);