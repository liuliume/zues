<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
<title>Account</title>
<!-- PAGE LEVEL STYLE REFERENCES -->
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">Account Management</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> Account Management
				</span> <i class="fa fa-angle-right"></i></li>
				<li><span> Account List </span></li>
			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-actions">
				<form id="form_search" class="form-horizontal"
					action="/account/list" method="GET">
					<div class="form-group">
						<label class="col-md-1 control-label">Name: </label>
						<div class="col-md-3">
							<input type="text" id="nameQ"
								class="form-control input-medium input-inline" name="nameQ"
								placeholder="Input name" value="${seed.filter['nameQ']}" />
						</div>
						<div class="pull-right">
							<a href="javascript:ZuesAccount.search();" class="btn dark">Search
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
					class="fa fa-plus"></i>Create</a> <a class="btn blue" href="#"
					id="deleteAccountBatch"><i class="fa fa-times"></i>Delete</a>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-calendar"></i> Account List
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
								<th style="width: 10%;">Name</th>
								<th style="width: 5%;">Email</th>
								<th style="width: 10%;">Mobile</th>
								<th style="width: 15%;">reg_time</th>
								<th style="width: 10%;">Gender</th>
								<th style="width: 15%;">Address</th>
								<th style="width: 20%;">Action</th>
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
									<td>${item.uniqname}</td>
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

	<!--确认modal，用于确认功能 ，仿照confirm-->
	<div id="confirmModal" class="modal fade" aria-hidden="true"
		tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title" id="confirmModalTitle">Delete Segment</h4>
				</div>
				<div class="modal-body">
					<div class="info" id="confirmModalContent">Please confirm if
						you want to delete the Segment?</div>
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

	<input type="hidden" id="setOptionUrl" value="${ctx}/account/" />
	
	<input type="hidden" id="accountId_delete"/>

	<input type="hidden" id="ctxUrl" value="${ctx}" />
	<!-- PAGE LEVEL JS REFERENCES -->
	<script src="${ctx}/resources/scripts/pages/account/accountList.js"
		type="text/javascript"></script>
</body>