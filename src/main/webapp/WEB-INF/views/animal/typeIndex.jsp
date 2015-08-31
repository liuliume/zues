<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>宠物管理</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">宠物管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 宠物管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 宠物类型管理 </span></li>

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
							<i class="fa fa-calendar"></i>创建宠物类型
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">类型: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="typeName" id="typeName"
												placeholder="请输入宠物类型" value="${type.typeName}"> <span
												class="help-block display-hide" id="errorInfoName">请输入宠物类型!</span> <span class="label label-warning">
												最长32个字符. </span>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">价格系数:<span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<input type="text" class="form-control" id="expenseCoefficient"
												name="expenseCoefficient" value="${type.expenseCoefficient}"> <span
												class="help-block display-hide" id="errorInfoExpense">请输入宠物类型价格系数!</span>
										</div>
									</div>

									<div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="id" name="id"
												value="${type.id}" />
											<a href="" class="btn green" id="btnConfirm" name="btnConfirm">确定</a>
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
	<script src="${ctx}/resources/scripts/pages/animal/animalTypeIndex.js"
		type="text/javascript"></script>
</body>

