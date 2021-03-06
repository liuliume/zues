<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
<title>订单管理</title>
<!-- PAGE LEVEL STYLE REFERENCES -->
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
				<li><i class="fa fa-home"></i> <span> 订单管理 </span> <i
					class="fa fa-angle-right"></i></li>
				<li><span> 订单列表 </span></li>
			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-actions">
				<form id="form_search" class="form-horizontal" action="/orders/list"
					method="GET">
					<div class="form-group">
						<label class="col-md-1 control-label">订单号: </label>
						<div class="col-md-3">
							<input type="text" id="id"
								class="form-control input-medium input-inline" name="id"
								placeholder="订单号" value="${seed.filter['id']}" />
						</div>
						<label class="col-md-1 control-label">用户名称: </label>
						<div class="col-md-3">
							<input type="text" id="account_name"
								class="form-control input-medium input-inline"
								name="account_name" placeholder="用户名称"
								value="${seed.filter['account_name']}" />
						</div>
						<label class="col-md-1 control-label">用户手机: </label>
						<div class="col-md-3">
							<input type="text" id="mobile"
								class="form-control input-medium input-inline"
								name="mobile" placeholder="用户手机号"
								value="${seed.filter['mobile']}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">下单时间: </label>
						<div class="col-md-3">
							<input type="text" id="create_time"
								class="form-control input-medium input-inline"
								name="create_time" placeholder="下单时间"
								value="${seed.filter['create_time']}" readonly="readonly" />
						</div>

						<div class="pull-right">
							<!-- <a href="javascript:ZuesAccount.search();" class="btn dark">搜索
								<i class="fa fa-search"></i>
							</a> -->
							<button class="btn dark">搜索
								<i class="fa fa-search"></i></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="blank-form-actions">
				<a class="btn green" href="#" id="create"><i
					class="fa fa-plus"></i>创建</a> <!-- <a class="btn blue" href="#"
					id="deleteBatch"><i class="fa fa-times"></i>删除</a> -->
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-calendar"></i> 用户列表
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<liuliume:pagination position="above"></liuliume:pagination>
					<table
						class="table table-bordered table-striped table-condensed flip-content"
						id="fromAccountTable">
						<thead class="flip-content">
							<tr>
								<th style="width: 42px;">
									<div>
										<span><input type="checkbox" id="selectAll"></span>
									</div>
								</th>
								<th style="width: 10%;">订单号</th>
								<th style="width: 5%;">订单类型</th>
								<th style="width: 5%;">下单人</th>
								<th style="width: 10%;">手机号</th>
								<th style="width: 10%;">宠物</th>
								<th style="width: 5%;">支付方式</th>
								<th style="width: 5%;">服务类型</th>
								<th style="width: 10%;">服务时间</th>
								<th style="width: 15%;">地址</th>
								<th style="width: 5%;">订单金额</th>
								<th style="width: 5%;">付款状态</th>
								<th style="width: 5%;">订单状态</th>
								<th style="width: 10%;">下单时间</th>
								<!-- <th style="width: 20%;">操作</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${seed.result }">
								<tr>
									<td>
										<div class="">
											<span><input class="" type="checkbox" id="test"
												value="${item.orderId}"> </span>
										</div>
									</td>
									<%-- <td><a href="/orders/index?ordersId=${item.orderId}&orderType=${item.orderType}">${item.orderId}</a></td> --%>
									<td><a href="/orders/orderDetail?ordersId=${item.orderId}&orderType=${item.orderType}">${item.orderId}</a></td>
									<td>${item.orderTypeEnum.desc }</td>
									<td>${item.account.uniqname }</td>
									<td>${item.account.mobile }</td>
									<td>${item.animalName }</td>
									<td><c:if test="${item.paymentType eq 1 }">微信支付</c:if><c:if test="${item.paymentType eq 2 }">到店支付</c:if></td>
									<td>${item.serviceTypeDesc }</td>
									<td>${item.serviceTime }</td>
									<td>${item.address }</td>
									<td>${item.cost }</td>
									<td>${item.paymentStatusDesc }</td>
									<td>${item.statusEnum.desc }</td>
									<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd hh:MM:ss"/> </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<liuliume:pagination position="below"></liuliume:pagination>
				</div>
			</div>
		</div>
	</div>

	<!-- 提示弹出框,用于弹出提示信息，仿照alert-->
			<div id="selectModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title" id="selectOrderType">请选择订单类型</h4>
						</div>
						<div class="modal-body">
							<div class="info form-group" id="alertModalContent">
								<label class="col-md-3 control-label">订单类型: </label>
								<div class="col-md-3">
									<select
												class="table-group-action-input form-control input-medium select2me"
												name="typeId" id="typeId">
												<c:forEach var="type" items="${orderTypes}">
													<option value="${type.id}"
														<c:if test='${type.id eq -1}'>selected</c:if>>
														${type.desc }</option>
												</c:forEach>
										</select> <span class="help-block display-hide" id="errorInfoType">请选择订单类型!</span>
								</div>
							</div>
							<div class="form-group">
								<div style="text-align: center">
											<button type="button" class="btn blue" data-dismiss="modal"
								id="btnConfirm">确认</button>
										</div>
							</div>
						</div>
						<!-- <div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal"
								id="btnConfirm">确认</button>
						</div> -->
					</div>
				</div>
			</div>
			<!-- 提示弹出框结束 -->

	<input type="hidden" id="setOptionUrl" value="${ctx}/orders/" />

	<input type="hidden" id="accountId_delete" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />
	<!-- PAGE LEVEL JS REFERENCES -->
	<script src="${ctx}/resources/scripts/pages/orders/ordersList.js"
		type="text/javascript"></script>
</body>