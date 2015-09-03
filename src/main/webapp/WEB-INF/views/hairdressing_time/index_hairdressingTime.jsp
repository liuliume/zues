<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>美容时间管理</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">美容时间管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 美容时间管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 美容时间 Index </span></li>

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
							<i class="fa fa-calendar"></i>美容时间管理
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">美容开始时间: <span
											class="required"> * </span>
										</label>
										<%--<div class="col-md-10">--%>
											<%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
												<%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
												<%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
												<%--最长128个字符. </span>--%>
										<%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="hairdressing_name"
                                                   name="startTime" value="${hairdressingTime.startTime}"> <span
                                                class="help-block display-hide" id="course_name_error">请输入美容类型!</span>
                                        </div>
									</div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">美容结束时间: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="course_describe"
                                                   name="endTime" value="${hairdressingTime.endTime}"> <span
                                                class="help-block display-hide" id="errorInfoAddress">请输入房间类型!</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">人数: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="expense"
                                                   name="servicePersionNum" value="${hairdressingTime.servicePersionNum}"> <span
                                                class="help-block display-hide" id="expense_error">请输入房间类型!</span>
                                        </div>
                                    </div>


                                    <div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="course_id" name="id"
												value="${hairdressingTime.id}" />
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
	<%--<div id="alertModal" class="modal fade">--%>
		<%--<div class="modal-dialog">--%>
			<%--<div class="modal-content">--%>
				<%--<div class="modal-header">--%>
					<%--<button type="button" class="close" data-dismiss="modal"--%>
						<%--aria-hidden="true"></button>--%>
					<%--<h4 class="modal-title" id="alertModalTitle">Create Account</h4>--%>
				<%--</div>--%>
				<%--<div class="modal-body">--%>
					<%--<div class="info" id="alertModalContent">Create Successfully!--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="modal-footer">--%>
					<%--<button type="button" class="btn blue" data-dismiss="modal"--%>
						<%--id="alertOkBtn">Confirm</button>--%>
				<%--</div>--%>

			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
	<!-- 提示弹出框结束 -->

	<input type="hidden" id="setOptionUrl" value="${ctx}/hairdressing_time/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/hairdressing_time/hairdressingTimeIndex.js"
		type="text/javascript"></script>
</body>

