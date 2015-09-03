<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>订单管理</title>
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
			<h3 class="page-title">订单管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 订单管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span> 订单 Index </span></li>

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
							<i class="fa fa-calendar"></i>宠物美容
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">宠物品种: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<select
												class="table-group-action-input form-control input-medium select2me"
												name="animalsId" id="animalsId">
												<c:forEach items="${allAnimals }" var="item">
													<option value="${item.id}"
														<c:choose>
															<c:when test='${empty orders }'>
																<c:if test='${item.id eq -1}'>selected</c:if>
															</c:when>
															<c:otherwise>
																<c:if test='${item.id eq orders.animalsId}'>selected</c:if>
															</c:otherwise>
														</c:choose>>
														${item.animalsName }</option>
												</c:forEach>
											</select> <span class="help-block display-hide" id="errorInfoAnimal">请选择宠物类型!</span>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">美容开始日期: <span
											class="required"> * </span>
										</label>
										<div class="col-md-3">
											<input type="text" class="form-control" id="startDate"
												name="startDate" value="${orders.startDate}"
												readonly="readonly"> <span
												class="help-block display-hide" id="errorInfoStartDate">请选择美容开始日期!</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">美容结束日期: <span
											class="required"> * </span>
										</label>
										<div class="col-md-3">
											<input type="text" class="form-control" id="endDate"
												name="endDate" value="${orders.endDate}" readonly="readonly">
											<span class="help-block display-hide" id="errorInfoEndDate">请选择美容结束日期!</span>
										</div>
									</div>

									

									<div class="form-group">
										<label class="col-md-2 control-label">订单账户: <span
											class="required"> * </span></label>
										<div class="col-md-10">
											<select
												class="table-group-action-input form-control input-medium select2me"
												name="accountId" id="accountId">
												<c:forEach items="${allAccounts }" var="item">
													<option value="${item.account_id}"
														<c:choose>
															<c:when test='${empty orders }'>
																<c:if test='${item.account_id eq -1}'>selected</c:if>
															</c:when>
															<c:otherwise>
																<c:if test='${item.account_id eq orders.accountId}'>selected</c:if>
															</c:otherwise>
														</c:choose>>
														${item.uniqname }</option>
												</c:forEach>
											</select> <span class="help-block display-hide" id="errorInfoAccount">请选择订单用户!</span>
										</div>
									</div>

									<div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="orderType" name="orderType"
												value="${orderType}" /> 
											<input type="hidden" id="orderId" name="orderId"
												value="${orders.orderId}" /> <a href=""
												class="btn green" id="btnConfirm" name="btnConfirm">保存</a>
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


	<input type="hidden" id="setOptionUrl" value="${ctx}/orders/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/orders/trainingIndex.js"
		type="text/javascript"></script>
</body>

