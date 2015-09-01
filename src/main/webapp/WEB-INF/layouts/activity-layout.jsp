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

<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/third-party/metronic/assets/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/pages/activity/profile.css" />

<sitemesh:head />

</head>

<body>
	<!-- HEADER LAYOUT -->
	<%@ include file="/WEB-INF/layouts/header/header.jsp"%>
	<!-- CONTENT LAYOUT -->
	<div class="page-container" style="margin-top: 50px;">
		<div class="page-content background-radial">
			<!-- PAGE LEVEL BODY SECTION -->
			<div class="alert alert-danger display-none" style="display: none;">
				<button class="close" data-dismiss="alert"></button>
				<div id="success-message">${ successMessage}</div>
				<div id="info-message">${ infoMessage}</div>
				<div id="warning-message">${ warningMessage}</div>
				<div id="error-message">${ errorMessage}</div>
			</div>

			<!--确认modal，用于确认功能 ，仿照confirm-->
			<div id="confirmModal" class="modal fade" aria-hidden="true"
				tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title" id="confirmModalTitle">Delete
								Account</h4>
						</div>
						<div class="modal-body">
							<div class="info" id="confirmModalContent">Please confirm
								if you want to delete the Account?</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn default" data-dismiss="modal">Cancel</button>
							<button type="button" class="btn blue" id="confirmOkBtn">Confirm</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 确认弹出框结束 -->
			<!-- 提示弹出框,用于弹出提示信息，仿照alert-->
			<div id="alertModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title" id="alertModalTitle">Delete Segment</h4>
						</div>
						<div class="modal-body">
							<div class="info" id="alertModalContent">Please choose the
								group to be deleted!</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal"
								id="alertOkBtn">Confirm</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 提示弹出框结束 -->
			<sitemesh:body />
		</div>
	</div>
	<%@ include file="/WEB-INF/layouts/footer/footer.jsp"%>
</body>
</html>