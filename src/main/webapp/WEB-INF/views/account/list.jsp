<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
<title>用户管理</title>
<!-- PAGE LEVEL STYLE REFERENCES -->
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">用户管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 用户管理
				</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 用户列表 </span></li>
			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-actions">
				<form id="form_search" class="form-horizontal"
					action="/account/list" method="GET">
					<div class="form-group">
						<label class="col-md-1 control-label">昵称: </label>
						<div class="col-md-3">
							<input type="text" id="nameQ"
								class="form-control input-medium input-inline" name="nameQ"
								placeholder="Input name" value="${seed.filter['nameQ']}" />
						</div>
						<label class="col-md-1 control-label">手机号: </label>
						<div class="col-md-3">
							<input type="text" id="mobileQ"
								class="form-control input-medium input-inline" name="mobileQ"
								placeholder="Input mobile" value="${seed.filter['mobileQ']}" />
						</div>
						<div class="pull-right">
							<a href="javascript:ZuesAccount.search();" class="btn dark">搜索
								<i class="fa fa-search"></i>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="blank-form-actions">
				<a class="btn green" href="#" id="createAccount"><i
					class="fa fa-plus"></i>创建</a> <a class="btn blue" href="#"
					id="deleteAccountBatch"><i class="fa fa-times"></i>删除</a>
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
								<th style="width: 10%;">昵称</th>
								<th style="width: 5%;">邮箱</th>
								<th style="width: 10%;">手机</th>
								<th style="width: 15%;">注册时间</th>
								<th style="width: 10%;">性别</th>
								<th style="width: 15%;">地址</th>
								<th style="width: 20%;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${seed.result }" var="item">
								<tr>
									<td>
										<div class="">
											<span><input class="" type="checkbox" id="test"
												value="${item.account_id}"> </span>
										</div>
									</td>
									<%-- <td>${item.uniqname}</td> --%>
									<td><a href="/account/index?account_id=${item.account_id }">${item.uniqname}</a></td>
									<td>${item.email}</td>
									<td>${item.mobile}</td>
									<td>${item.reg_time}</td>
									<td>${item.gender}</td>
									<td>${item.address}</td>

									<td><a class="btn default btn-xs blue-stripe" href="#"
										name="editAccount" accountId="${item.account_id}"><i
											class="fa fa-edit"></i> Edit</a> <a
										class="btn default btn-xs purple-stripe" href="#"
										name="deleteAccount" accountId="${item.account_id}"><i
											class="fa fa-times"></i> Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<liuliume:pagination position="below"></liuliume:pagination>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="setOptionUrl" value="${ctx}/account/" />
	
	<input type="hidden" id="ctxUrl" value="${ctx}" />
	<!-- PAGE LEVEL JS REFERENCES -->
	<script src="${ctx}/resources/scripts/pages/account/accountList.js"
		type="text/javascript"></script>
</body>