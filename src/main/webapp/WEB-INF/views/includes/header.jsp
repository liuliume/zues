<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liuliume" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${title}</title>
	    <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${ctx}/resources/favicon.ico"  type="image/x-icon" />
		<link rel="Bookmark" href="${ctx}/resources/favicon.ico" />
	
		<!-- css -->
		<link href="${ctx}/resources/third-party/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/resources/third-party/bootstrap/css/bootstrap.extension.css" rel="stylesheet">
		<!--link href="${ctx}/resources/third-party/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"-->
		<link href="${ctx}/resources/third-party/datatable/css/jquery.dataTables.css" rel="stylesheet">
<%-- 		<link href="${ctx}/resources/third-party/datatable/css/jquery.dataTables.ext.css" rel="stylesheet"> --%>
		<link href="${ctx}/resources/themes/default/css/bootstrap.ext.css" rel="stylesheet">
<%--         <link href="${ctx}/resources/third-party/jquery/css/jquery-ui-1.9.2.custom.css" rel="stylesheet"> --%>
        <link href="${ctx}/resources/third-party/datepicker/css/ui.daterangepicker.css" rel="stylesheet">
		<link href="${ctx}/resources/css/header.css" rel="stylesheet">
        <link href="${ctx}/resources/third-party/validate/validate.css" rel="stylesheet">
		
		<link href="${ctx}/resources/third-party/jquery/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
		<link href="${ctx}/resources/themes/default/css/ui.baseinfo.setting.css" rel="stylesheet">
		
        <!--[if lt IE 9]>
        <link rel="stylesheet" href="${ctx}/resources/third-party/jquery/css/jquery.ui.1.9.2.ie.css">
        <![endif]-->

		<!-- script -->
		<%-- <script src="${ctx}/resources/third-party/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> --%>
		<script src="${ctx}/resources/third-party/bootstrap/js/bootstrap.min.js"></script>
		<script src="${ctx}/resources/third-party/template/jquery.tmpl.min.js" type="text/javascript"></script>
<%-- 		<script src="${ctx}/resources/third-party/jquery/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script> --%>
		<script src="${ctx}/resources/third-party/datatable/js/jquery.dataTables.js" type="text/javascript"></script>
		<script src="${ctx}/resources/third-party/datatable/js/jquery.dataTables.defaults.js" type="text/javascript"></script>
		<script src="${ctx}/resources/third-party/datatable/js/jquery.dataTables.plugin.js" type="text/javascript"></script>
		<script src="${ctx}/resources/third-party/datatable/js/ellipses.js" type="text/javascript"></script>
		<script src="${ctx}/resources/third-party/form/jquery.form.js" type="text/javascript"></script>
		<script src="${ctx}/resources/third-party/datepicker/js/date.js" type="text/javascript"></script>
		<script src="${ctx}/resources/third-party/datepicker/js/daterangepicker.jQuery.js" type="text/javascript"></script>
        <script src="${ctx}/resources/third-party/validate/jquery.validate.js" type="text/javascript"></script>
        <script src="${ctx}/resources/third-party/validate/messages_cn.js" type="text/javascript"></script>
        <script src="${ctx}/resources/third-party/validate/jquery.metadata.js" type="text/javascript"></script>
        <script src="${ctx}/resources/third-party/myArray.js" type="text/javascript"></script>
        
        <script src="${ctx}/resources/third-party/jquery/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
        <script src="${ctx}/resources/js/ajax_err.js" type="text/javascript"></script>
        <script type="text/javascript" src="${ctx}/resources/js/util.js"></script>
        
       <link href="${ctx}/resources/third-party/metronic/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
		<style type="text/css">
		
		#adaptMenu .dropdown-menu {
			position: absolute;
			top: 100%;
			left: 0;
			z-index: 1000;
			float: left;
			list-style: none;
			text-shadow: none;
			padding: 0px;
			margin: 0px;
			background-color: #ffffff;
			-webkit-box-shadow: 0 1px 8px rgba(0, 0, 0, 0.1);
			-moz-box-shadow: 0 1px 8px rgba(0, 0, 0, 0.1);
			box-shadow: 0 1px 8px rgba(0, 0, 0, 0.1);
			font-size: 14px;
			font-family: "Segoe UI",Helvetica, Arial, sans-serif;
			border: 1px solid #ddd;
		}

		#adaptMenu .dropdown-menu li > a {
			padding: 6px 0 6px 13px;
			color: #333;
			text-decoration: none;
			display: block;
			clear: both;
			font-weight: normal;
			line-height: 18px;
			white-space: nowrap;
		}

		#adaptMenu{
			font-family: 微软雅黑,'Open Sans', sans-serif,Verdana,宋体;
		}
		#adaptMenu .navbar-fixed-top,  #adaptMenu .navbar-fixed-bottom{
			position: fixed;
			right: 0;
			left: 0;
			z-index: 1030;
		}
		#adaptMenu .navbar-fixed-top {
			top: 0;
			border-width: 0 0 1px;
		}
		
		#adaptMenu .navbar-inverse{
			background-color: #222;
			border-color: #080808;
		}
		
		#adaptMenu .header{
			filter: none !important;
			background-image: none !important;
			background-color: #212121 !important;
		}
		
		#adaptMenu .navbar-header {
			float: left;
		}
		
		#adaptMenu .navbar-collapse {
			max-height: 340px;
			overflow-x: visible;
			padding-right: 15px;
			padding-left: 15px;
			border-top: 1px solid transparent;
			box-shadow: inset 0 1px 0 rgba(255,255,255,.1);
			-webkit-overflow-scrolling: touch;
		}

		#adaptMenu .navbar-inner {
			margin-top: -16px;
		}

		#adaptMenu .navbar-collapse {
			width: auto;
			border-top: 0;
			box-shadow: none;
		}

		#adaptMenu .navbar-collapse.collapse{
			display: block!important;
			height: auto!important;
			padding-bottom: 0;
			overflow: visible!important;
		}
		
		#adaptMenu .navbar-fixed-top .navbar-collapse, #adaptMenu .navbar-static-top .navbar-collapse, #adaptMenu .navbar-fixed-bottom .navbar-collapse{
			padding-left: 0;
			padding-right: 0;
		}
		
		#adaptMenu .navbar-inverse .navbar-collapse, #adaptMenu .navbar-inverse .navbar-form{
			border-color: #101010;
		}
		
		#adaptMenu .nav {
			margin-bottom: 0;
			padding-left: 0;
			list-style: none;
		}

		#adaptMenu .navbar-nav {
			margin: 7.5px -15px;
		}

		#adaptMenu .navbar-nav {
			float: left;
			margin: 0;
		}
		
		#adaptMenu .header .navbar-nav{
			margin-right: 20px;
			display: block;
		}
		
		#adaptMenu li {
			display: list-item;
			text-align: -webkit-match-parent;
			border-radius: 0 !important;
			box-sizing: border-box;
			border-radius: 0;
			box-shadow: none;
			border: 0;
			list-style: none;
			text-shadow: none;
		}

		#adaptMenu .nav>li {
			position: relative;
			display: block;
		}
		
		#adaptMenu .navbar-nav>li {
			float: left;
		}
		
		#adaptMenu a{
			outline: 0;
			text-shadow: none !important;
			color: #0d638f;
			background: 0 0;
		}
		
		#adaptMenu .navbar-brand {
			float: left;
			padding: 15px;
			font-size: 18px;
			line-height: 20px;
			height: 50px;
		}

		#adaptMenu .nav>li>a {
			position: relative;
			display: block;
			padding: 10px 15px;
		}

		#adaptMenu .navbar-nav>li>a {
			padding-top: 10px;
			padding-bottom: 10px;
			line-height: 20px;
		}

		#adaptMenu .navbar-nav>li>a {
			padding-top: 15px;
			padding-bottom: 15px;
		}

		#adaptMenu ul.navbar-nav a {
			font-size: 16px;
		}

		#adaptMenu .navbar-inverse .navbar-brand {
			color: #999;
		}

		#adaptMenu .navbar-inverse .navbar-nav>li>a {
			color: #999;
		}
		
		#adaptMenu button{
			font-family: inherit;
			font-size: inherit;
			line-height: inherit;
			-webkit-appearance: button;
			cursor: pointer;
			border-radius: 0 !important;
			text-transform: none;
			overflow: visible;
			color: inherit;
			font: inherit;
			margin: 0;
			box-sizing: border-box;
			align-items: flex-start;
			text-align: center;
		}
		
		#adaptMenu .navbar-toggle {
			position: relative;
			float: right;
			margin-right: 15px;
			padding: 9px 10px;
			margin-top: 8px;
			margin-bottom: 8px;
			background-color: transparent;
			background-image: none;
			border: 1px solid transparent;
			border-radius: 4px;
		}

		#adaptMenu .navbar-toggle {
			display: none;
		}

		#adaptMenu .navbar-inverse .navbar-toggle{
			border-color: #333;
		}
		
		#adaptMenu .nav {
			margin-bottom: 0;
			padding-left: 0;
			list-style: none;
		}

		#adaptMenu .navbar-nav {
			float: left;
			margin: 0;
		}

		#adaptMenu .pull-right {
			float: right!important;
			content: " ";
			display: table;
		}
		
		#adaptMenu .dropdown-menu li > a {
			padding: 6px 0 6px 13px;
			color: #333;
			text-decoration: none;
			display: block;
			clear: both;
			font-weight: normal;
			line-height: 18px;
			white-space: nowrap;
		}

		#adaptMenu .dropdown-menu{
			font-family: 微软雅黑,'Open Sans', sans-serif,Verdana,宋体;
		}
		
		#adaptMenu .navbar-nav>li>.dropdown-menu {
			margin-top: 0;
			border-top-right-radius: 0;
			border-top-left-radius: 0;
		}

		#adaptMenu li.user .dropdown-menu {
			min-width: 140px;
		}

		#adaptMenu .nav.pull-right > li > .dropdown-menu, #adaptMenu .nav > li > .dropdown-menu.pull-right {
			right: 0;
			left: auto;
		}
		
		#adaptMenu .arrowDown {
			border-top: #fff 5px solid;
			border-left: transparent 5px solid;
			border-right: transparent 5px solid;
			border-bottom: transparent 5px solid;
			width: 0;
			height: 0;
			line-height: 0;
			font-size: 0;
			margin-left: 3px;
		}

		#adaptMenu .dropup.open > .dropdown-toggle, #adaptMenu .dropdown.open > .dropdown-toggle {
			border-color: #ddd !important;
		}

		#adaptMenu .navbar-inverse .navbar-nav>li>a:hover, #adaptMenu .navbar-inverse .navbar-nav>li>a:focus{
			color: #fff;
		}

		#adaptMenu .navbar-inverse .navbar-nav>.open>a, #adaptMenu .navbar-inverse .navbar-nav>.open>a:hover, #adaptMenu .navbar-inverse .navbar-nav>.open>a:focus {
			background-color: #080808;
			color: #fff;
		}

		#adaptMenu .header .navbar-nav .dropdown-toggle:hover, #adaptMenu .header .navbar-nav .dropdown.open .dropdown-toggle {
			background-color: #383838 !important;
		}

		#adaptMenu .header .navbar-nav li.dropdown .dropdown-toggle i {
			color: #8a8a8a !important;
		}
		
		#adaptMenu .dropdown-menu li{
			text-align:left;
		}
		
		#adaptMenu .dropdown-menu li > a:hover, #adaptMenu .dropdown-menu .active > a, #adaptMenu .dropdown-menu .active > a:hover {
			text-decoration: none;
			background-image: none;
			background-color: #eee;
			color: #333;
			filter: none;
			text-align:left;
		}
		
		#adaptMenu .page-header-fixed{
			
		}
		
		#adaptMenu .header .navbar-nav li.dropdown .dropdown-toggle i {
			color: #8a8a8a !important;
		}
		
		#adaptMenu span.username{
			margin-left:6px;
		}
		
		li [class^="fa-"], li [class*=" fa-"] {
			display: inline-block;
			width: 1.25em;
			text-align: center;
		}
		
		[class^="fa-"], [class*=" fa-"] {
			display: inline-block;
			margin-top: 1px;
			font-size: 14px;
			line-height: 14px;
		}

		</style>
	</head>

	<body>

		<div id="adaptMenu">
			<div class="header navbar-inverse navbar-fixed-top" style="height:50px;">
			   <div class="navbar-header">
			     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			       <span class="sr-only">Toggle navigation</span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			     </button>
			   </div>
			   <jsp:include page="/includes/newmenu" />
			</div>
		</div>
		<div style="margin-top:50px;"/>