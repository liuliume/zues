<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>地址管理</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">地址管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 地址管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 地址 Index </span></li>

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
							<i class="fa fa-calendar"></i>地址管理
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">地址名称: <span
											class="required"> * </span>
										</label>
										<%--<div class="col-md-10">--%>
											<%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
												<%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
												<%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
												<%--最长128个字符. </span>--%>
										<%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="name"
                                                   name="name" value="${address.name}"> <span
                                                class="help-block display-hide" id="errorInfoAddress">请输入用户地址!</span>
                                        </div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">级别: <span
											class="required"> * </span>
										</label>
										<div class="col-md-10">
											<select
												class="table-group-action-input form-control input-medium select2me"
												name="level" id="level">
												<c:forEach var="addressLevel" items="${allAddressLevel}">
													<option value="${addressLevel.value.level}"
														<c:if test='${addressLevel.value.level == "1"}'>selected</c:if>>${addressLevel.value.desc}</option>
												</c:forEach>

											</select>
											<%--<span class="help-block display-hide" id="errorInfoGender">请选择性别!</span>--%>
										</div>
									</div>

                                    <div class="form-group" id="parent_first_div" style="display:none">
                                        <label class="col-md-2 control-label">一级地址: <span
                                                class="required"> * </span>
                                        </label>
                                        <div class="col-md-10">
                                            <select
                                                    class="table-group-action-input form-control input-medium select2me"
                                                    name="parent_first_id" id="parent_first_id">
                                                <option value='' selected="selected">请选择</option>
                                                <c:forEach var="addressfirst" items="${firstAddress}">
                                                    <option value="${addressfirst.id}">${addressfirst.name}</option>
                                                </c:forEach>

                                            </select>
                                            <span class="help-block display-hide" id="parent_address_first_error">请选择一级地址</span>
                                        </div>
                                    </div>

                                    <div class="form-group" id="parent_second_div" style="display:none">
                                        <label class="col-md-2 control-label">二级地址: <span
                                                class="required"> * </span>
                                        </label>
                                        <div class="col-md-10">
                                            <select
                                                    class="table-group-action-input form-control input-medium select2me"
                                                    name="parent_first_id" id="parent_second_id">
                                                <option value='' selected="selected">请选择</option>
                                                <c:forEach var="addresssecond" items="${secondAddress}">
                                                    <option value="${addresssecond.id}">${addresssecond.name}</option>
                                                </c:forEach>

                                            </select>
                                            <span class="help-block display-hide" id="parent_address_second_error">请选择二级地址</span>
                                        </div>
                                    </div>


                                    <div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="address_id" name="address_id"
												value="${address.id}" />
											<a href="" class="btn green" id="btnConfirm1" name="btnConfirm">创建</a>
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
					<h4 class="modal-title" id="alertModalTitle">Create Account</h4>
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

	<input type="hidden" id="setOptionUrl" value="${ctx}/code/address/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/code/addressIndex.js"
		type="text/javascript"></script>
</body>

