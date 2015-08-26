<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>liuliume 平台 | <sitemesh:title /></title>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link rel="shortcut icon" href="${ctx }/resources/favicon.ico" />

<%@ include file="/WEB-INF/layouts/fragment/common-css.jsp"%>
<%@ include file="/WEB-INF/layouts/fragment/common-js.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/metronic/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/pages/activity/profile.css" />


<sitemesh:head />
</head>

<body>
	<!-- HEADER LAYOUT -->
	<%@ include file="/WEB-INF/layouts/header/header.jsp"%>
     <!-- CONTENT LAYOUT -->
	<div class="page-container"  style="margin-top:50px;">
		<div class="page-content background-radial">
			<!-- PAGE LEVEL BODY SECTION -->
			<div class="alert alert-danger display-none" style="display: none;">
				<button class="close" data-dismiss="alert"></button>
				<div id="success-message">${ successMessage}</div>
				<div id="info-message">${ infoMessage}</div>
				<div id="warning-message">${ warningMessage}</div>
				<div id="error-message">${ errorMessage}</div>
			</div>
			<sitemesh:body />
		</div>
	</div>
	<%@ include file="/WEB-INF/layouts/footer/footer.jsp"%>
</body>
</html>