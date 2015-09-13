<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>用用户管理</title>
<script type="text/javascript"
	src="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2-metronic.css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">用户管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 用户管理</span> <i
					class="fa fa-angle-right"></i></li>
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
											<input type="text" class="form-control" name="uniqname"
												id="uniqname" placeholder="请输入用户昵称"
												value="${account.uniqname}"> <span
												class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span>
											<span class="label label-warning"> 最长128个字符. </span>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">性别: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<select
												class="table-group-action-input form-control input-medium select2me"
												name="gender" id="gender">
												<c:forEach var="genderEntry" items="${allGender}">
													
													<option value="${genderEntry.value.id }"
															<c:choose>
															<c:when test='${empty account }'>
																<c:if test='${genderEntry.value.desc eq "All"}'>selected</c:if>
															</c:when>
															<c:otherwise>
																<c:if test='${genderEntry.value.id eq account.gender}'>selected</c:if>
															</c:otherwise>
														</c:choose>>${genderEntry.value.desc}
														
													</option>
												</c:forEach>

											</select> <span class="help-block display-hide" id="errorInfoGender">请选择性别!</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">用户地址: </label>
										<div class="col-md-10">
											<div class="col-md-3">
												<!-- province -->
												<select
													class="table-group-action-input form-control input-medium select2me"
													name="province_id" id="province_id">
													<c:forEach var="item" items="${allProvince }">
														<option value="${item.id }"
															<c:choose>
															<c:when test='${empty account }'>
																<c:if test='${item.id eq -1}'>selected</c:if>
															</c:when>
															<c:otherwise>
																<c:if test='${item.id eq account.province_id}'>selected</c:if>
															</c:otherwise>
														</c:choose>>
															${item.name }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-3">
												<!-- city -->
												<select
													class="table-group-action-input form-control input-medium select2me"
													name="city_id" id="city_id">
													<c:if test="${!empty account }">
														<option value="${account.city_id }">${account.city }</option>
													</c:if>
												</select>
											</div>
											<div class="col-md-3">
												<!-- area -->
												<select
													class="table-group-action-input form-control input-medium select2me"
													name="area_id" id="area_id">
													<c:if test="${!empty account }">
														<option value="${account.area_id }">${account.area }</option>
													</c:if>
												</select>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">详细地址:
										</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="address"
												name="address" value="${account.address}"> <span
												class="help-block display-hide" id="errorInfoAddress">请输入用户地址!</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">手机: <span
											class="required"> * </span>
										</label>
										<div class="col-md-3">
											<input type="text" class="form-control" id="mobile"
												name="mobile" value="${account.mobile }"> <span
												class="help-block display-hide" id="errorInfoMobile">请输入用户手机号!</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">邮箱: <span
											class="required"> * </span>
										</label>
										<div class="col-md-3">
											<input type="text" class="form-control" id="email"
												name="email" value="${account.email }">
												<span
												class="help-block display-hide" id="errorInfoEmail">用户邮箱格式错误!</span>
										</div>
									</div>

									<div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="account_id" name="account_id"
												value="${account.account_id}" /> <a href=""
												class="btn green" id="btnConfirm" name="btnConfirm">创建</a>
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


	<input type="hidden" id="setOptionUrl" value="${ctx}/account/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/account/accountIndex.js"
		type="text/javascript"></script>
</body>

