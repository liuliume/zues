<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>


<head>
<title>房间类型管理</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">房间类型管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 房间类型管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 房间类型 Index </span></li>

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
							<i class="fa fa-calendar"></i>房间类型管理
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<div class="tab-content no-space">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">房间类型名称: <span
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
                                                   name="roomName" value="${room.roomName}"> <span
                                                class="help-block display-hide" id="errorInfoAddress">请输入房间类型!</span>
                                        </div>
									</div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">费用: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="cost"
                                                   name="cost" value="${room.cost}"> <span
                                                class="help-block display-hide" id="cost_error">请输入房间类型!</span>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-md-2 control-label">微信折扣: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="weixin_discount"
                                                   name="weixinDiscount" value="${room.weixinDiscount}"> <span
                                                class="help-block display-hide" id="weixin_discount_error">请输入房间类型!</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">30天折扣: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="discount_30"
                                                   name="discount30" value="${room.discount30}"> <span
                                                class="help-block display-hide" id="discount_30_error">请输入房间类型!</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">90天折扣: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="discount_90"
                                                   name="discount90" value="${room.discount90}"> <span
                                                class="help-block display-hide" id="discount_90_error">请输入房间类型!</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">180天折扣: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="discount_180"
                                                   name="discount180" value="${room.discount180}"> <span
                                                class="help-block display-hide" id="discount_180_error">请输入房间类型!</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 control-label">房间数量: <span
                                                class="required"> * </span>
                                        </label>
                                        <%--<div class="col-md-10">--%>
                                        <%--<input type="text" class="form-control" name="uniqname" id="uniqname"--%>
                                        <%--placeholder="请输入用户昵称" value="${account.uniqname}"> <span--%>
                                        <%--class="help-block display-hide" id="errorInfoName">请输入用户昵称!</span> <span class="label label-warning">--%>
                                        <%--最长128个字符. </span>--%>
                                        <%--</div>--%>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="room_num"
                                                   name="roomNum" value="${room.roomNum}"> <span
                                                class="help-block display-hide" id="room_num_error">请输入房间类型!</span>
                                        </div>
                                    </div>


                                    <div class="form-group">
										<div style="text-align: center">
											<input type="hidden" id="room_id" name="id"
												value="${room.id}" />
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

	<input type="hidden" id="setOptionUrl" value="${ctx}/room/" />

	<input type="hidden" id="ctxUrl" value="${ctx}" />

	<input type="hidden" id="nameExists" value="0">
	<script src="${ctx}/resources/scripts/pages/room/roomIndex.js"
		type="text/javascript"></script>
</body>

