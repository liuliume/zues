<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>订单详情</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">订单管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 订单管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span> 订单 详情 </span></li>

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
							<i class="fa fa-calendar"></i>查看订单
						</div>
						<div class="actions btn-set">
							<button class="btn default" id="">
								<i class="fa fa-reply"></i> 置无效
							</button>
							<button class="btn green" type="button" id="BtnTransfer">
								<i class="fa fa-share"></i> 转发
							</button>
							<c:if test="${orders.paymentStatus eq 0 }">
								<button class="btn green" type="button" id="btnPay">
									<i class="fa fa-check-circle"></i> 收款
								</button>
							</c:if>
							<div class="btn-group">
								<a class="btn yellow" href="#" data-toggle="dropdown"> More
									<i class="fa fa-angle-down"></i>
								</a>
								<ul class="dropdown-menu pull-right">
									<li><a href="#" name="delete" id="delete"> Delete </a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<input type="hidden" value="${orders.orderId }" id="orderId" />
									<input type="hidden" value="${orders.paymentStatus }" id="paymentStatus" />

									<div class="form-group">
										<label class="col-md-2 control-label">订单类型: </label>
										<div class="col-md-3">
											<label class="col-md-2 form-control">${orders.orderTypeEnum.desc }</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">宠物品种: </label>
										<div class="col-md-3">
											<label class="form-control">${orders.animalName }</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">订单日期: </label>
										<div class="col-md-3">
											<label class="form-control">${orders.startDate} ~
												${orders.endDate }</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">下单时间: </label>
										<div class="col-md-3">
											<label class="form-control"><fmt:formatDate
													value="${orders.createTime }" pattern="yyyy-MM-dd" /></label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">下单人: </label>
										<div class="col-md-3">
											<label class="form-control">${orders.account.uniqname }</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">联系电话: </label>
										<div class="col-md-3">
											<label class="form-control">${orders.account.mobile }</label>
										</div>
									</div>

									<c:if test="${orders.orderType eq 1 }">
										<!-- 寄养类型 -->
										<div class="form-group">
											<label class="col-md-2 control-label">寄养房间: </label>
											<div class="col-md-3">
												<label class="form-control">${orders.room.roomName }</label>
											</div>
										</div>
									</c:if>
									<c:if test="${orders.orderType eq 2 }">
										<!-- 训练 -->
										<div class="form-group">
											<label class="col-md-2 control-label">课程类型: </label>
											<div class="col-md-3">
												<label class="form-control">${orders.course.courseName }</label>
											</div>
										</div>
									</c:if>
									<c:if test="${orders.orderType eq 3 }">
										<!-- 美容 -->
										<div class="form-group">
											<label class="col-md-2 control-label">服务项目: </label>
											<div class="col-md-3">
												<label class="form-control">${orders.hairdressing.hairdressingName }</label>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-2 control-label">服务方式: </label>
											<div class="col-md-3">
												<label class="form-control">${orders.serviceTypeDesc }</label>
											</div>
										</div>

										<c:if test="${orders.serviceType eq q }">
											<div class="form-group">
												<label class="col-md-2 control-label">上门地址: </label>
												<div class="col-md-3">
													<label class="form-control">${orders.address }</label>
												</div>
											</div>
										</c:if>
									</c:if>

									<div class="form-group">
										<label class="col-md-2 control-label">订单状态: </label>
										<div class="col-md-3">
											<label class="form-control">${orders.statusEnum.desc }</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">付款状态: </label>
										<div class="col-md-3">
											<label class="form-control"> <c:if
													test="${orders.paymentStatus eq 0 }">未付款</c:if> <c:if
													test="${orders.paymentStatus eq 1 }">已付款</c:if>
											</label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">订单金额: </label>
										<div class="col-md-3">
											<label class="form-control">${orders.cost }</label>
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
	<script src="${ctx}/resources/scripts/pages/orders/orderDetail.js"
		type="text/javascript"></script>
</body>

