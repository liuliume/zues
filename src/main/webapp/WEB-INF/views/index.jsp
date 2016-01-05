<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Index</title>
<script src="${ctx}/resources/login/js/jquery-1.11.1.min.js"></script>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ include file="/WEB-INF/views/includes/conflict-includes.jsp"%>
<%@ include file="/WEB-INF/views/includes/util.jsp"%>

<link href="${ctx}/resources/css/index.css" rel="stylesheet">
<style>
    .work-table .around .around-left {
        width:150px;
    }
</style>

<script type="text/javascript">
var page_size = 6;
var current_page = 0;
$(document).ready(function(){
    $.post('${ctx}/procurement-order/getorderstats',
            {
                viewType:"view_type_normal"
            },
            function(data) {
                $('#unreviewedOrdersLink').text(data.unreviewed);
            }
    );
	
    $.get('${ctx}/count_notify', function(response) {
        var pages = Math.ceil(response/page_size);
        if (pages > 5) pages = 5;
        $("#split_page").append('<li><a id="page_btn_before" href="#">&laquo;</a></li>');
        for (var i=1 ; i<=pages; i++) {
            $("#split_page").append('<li><a class="page_btn"  href="#"'+((i-1)==current_page?'style="background-color:#BBBBBB"':'')+'>'+i+'</a></li>');
        }
        $("#split_page").append('<li><a id="page_btn_after" href="#">&raquo;</a></li>');
        $(".page_btn").click(function(){
            $('.page_btn:eq('+current_page+')').removeAttr("style");
            current_page = $(this).text()-1;
            $('.page_btn:eq('+current_page+')').css({'background-color':'#BBBBBB'});
            $(".content").remove();
            $.post('${ctx}/list_notify', {start:current_page*page_size, limit:page_size}, function(response) {
                for (var key in response.data) {
                    var item = response.data[key];
                    console.log(item);
                    var div_prefix;
                    
                  
                    if (item.isTop == 1) {
                        div_prefix = '<div class="content"><div class="circle"/>';
                    }else{
                        div_prefix = '<div class="content normal-text">';
                    }
                    var div_date = '(' + item.createTime + ')';
                    $(".pagination").before(div_prefix+'<span class="notify"><a href="${ctx}/notify_detail?id='+item.id+'" target="_self">'+item.title+div_date
                    		+item.content+'</span></div>');
                }
            });
        });

        $("#page_btn_before").click(function(){
           if (current_page <=0) return false;
           $(".page_btn:eq("+(current_page-1)+")").click();
        });
        $("#page_btn_after").click(function(){
            if (current_page >= $(".page_btn").length-1) return false;
            $(".page_btn:eq("+(current_page+1)+")").click();
        });

        $(".page_btn:eq(0)").click();
        
    });

    $('.around').mouseenter(function(){
        $(this).removeClass('no-border');
    });

    $('.around').mouseleave(function(){
       $(this).addClass('no-border');
    });
});
</script>
</head>

    <body>

	<div class="wrap-out">
		<div class="work-table">
			<h2>工作台</h2>
			<div class="around no-border">
				<!-- <div class="around-left">预处理清单：</div> -->
				<div class="around-left">ToDo list:：</div>
				<div class="around-right"><a href="${ctx}/procurement-order/unreviewed" id="unreviewedOrdersLink">...</a></div>
			</div>
		</div>
		

		<div class="help-center">
			<h2>帮助中心</h2>
			<p class="other-tips">
                <%-- <a href="${ctx}/resources/supplierManual.pdf">供应商手册</a> --%>
                <a href="${ctx}/resources/supplierManual.pdf">使用手册</a>
				<a href="${ctx}/resources/supplierHelper.doc">帮助文档</a>
				<%-- <a href="${ctx}/resources/faq.doc">供应商平台常见问题</a> --%>
				<a href="${ctx}/resources/faq.doc">Liuliume平台常见问题</a>
			</p>
			<h2>举报投诉</h2>
			<p>
				<h4>邮箱：</h4>
				<p>zues@moonlight.com</p>
			</p>
		</div>


		<div class="notice-center">
			<h2>通知</h2>
			<div class="pagination">
			  <ul id="split_page">
			  </ul>
			</div>
		</div>

	</div>
    
	</body>
</html>

