<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>用户 Index</title>
<script type="text/javascript" src="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2-metronic.css"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">用户管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 用户管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 用户 Index </span></li>

			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<form class="form-horizontal form-row-seperated" action="#"
				id="IndexForm" enctype="multipart/form-data" method="post">
				<div class="portlet">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-calendar"></i>创建用户
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">昵称: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="name" id="name"
												placeholder="请输入用户昵称" value="${account.uniqname}"> <span
												class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">
												最长128个字符. </span>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">性别: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<select
												class="table-group-action-input form-control input-medium"
												name="gender" id="gender">
												<c:forEach var="genderEntry" items="${allGender}">
													<option value="${genderEntry.value.code}"
														<c:if test='${genderEntry.value.desc eq "All"}'>selected</c:if>>${genderEntry.value.desc}</option>
												</c:forEach>

											</select>
											<span class="help-block display-hide" id="errorInfoGender">请选择性别!</span>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">用户地址:
										</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="address"
												name="address"> <span
												class="help-block display-hide" id="errorInfoAddress">请填写用户地址!</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">手机: <span
											class="required"> * </span>
										</label>
										<div class="col-md-3">
											<input type="text" class="form-control" id="mobile"
												name="mobile"> <span
												class="help-block display-hide" id="errorInfoMobile">请输入用户手机号!</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">手机: <span
											class="required"> * </span>
										</label>
										<div class="col-md-3">
											<input type="text" class="form-control" id="mobile"
												name="mobile"> <span
												class="help-block display-hide" id="errorInfoMobile">请输入用户手机号!</span>
										</div>
									</div>

									<div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="account_id" name="account_id"
												value="${account_id.id}" />
											<button class="btn green" id="btnConfirm" name="btnConfirm">Create</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 提示弹出框,用于弹出提示信息，仿照alert-->
	<div id="alertModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title" id="alertModalTitle">Create User Data</h4>
				</div>
				<div class="modal-body">
					<div class="info" id="alertModalContent">Create Successfully!
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn blue" data-dismiss="modal"
						id="alertOkBtn">Confirm</button>
				</div>

			</div>
		</div>
	</div>
	<!-- 提示弹出框结束 -->

	<input type="hidden" id="setOptionUrl" value="${ctx}/retention/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/userdata/userdatIndex.js"
		type="text/javascript"></script>
</body>

