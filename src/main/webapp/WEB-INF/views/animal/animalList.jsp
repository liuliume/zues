<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
<title>宠物管理</title>
    <script type="text/javascript"
            src="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.css" />
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2-metronic.css" />
<!-- PAGE LEVEL STYLE REFERENCES -->
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-title">宠物管理</h3>
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="fa fa-home"></i> <span> 宠物管理
				</span> <i class="fa fa-angle-right"></i></li>
				<li><span> 宠物列表 </span></li>
			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="form-actions">
				<form id="form_search" class="form-horizontal"
					action="/animal/listAnimals" method="GET">
					<div class="form-group">
						<label class="col-md-1 control-label">宠物名称: </label>
						<div class="col-md-3">
							<input type="text" id="nameQ"
								class="form-control input-medium input-inline" name="nameQ"
								placeholder="宠物名称" value="${seed.filter['nameQ']}" />
						</div>
                        <label class="col-md-1 control-label">首字母: </label>
                        <div class="col-md-3">
                            <select
                                    class="table-group-action-input form-control input-medium select2me"
                                    name="orderType" id="orderType">
                                <option value="">请选择</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                                <option value="K">K</option>
                                <option value="L">L</option>
                                <option value="M">M</option>
                                <option value="N">N</option>
                                <option value="O">O</option>
                                <option value="P">P</option>
                                <option value="Q">Q</option>
                                <option value="R">R</option>
                                <option value="S">S</option>
                                <option value="T">T</option>
                                <option value="U">U</option>
                                <option value="V">V</option>
                                <option value="W">W</option>
                                <option value="X">X</option>
                                <option value="Y">Y</option>
                                <option value="Z">Z</option>
                            </select>
                        </div>
						<div class="pull-right">
							<!-- <a href="javascript:typeList.search();" class="btn dark">搜索
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
					class="fa fa-plus"></i>新增</a> <a class="btn blue" href="#"
					id="deleteBatch"><i class="fa fa-times"></i>删除</a>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-calendar"></i> 宠物列表
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
								<th style="width: 10%;">宠物Id</th>
								<th style="width: 20%;">宠物名称</th>
								<th style="width: 10%;">宠物类型</th>
                                <th style="width: 20%;">宠物首字母</th>
                                <th style="width: 20%;">排序</th>
								<th style="width: 20%;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${seed.result }" var="item">
								<tr>
									<td>
										<div class="">
											<span><input class="" type="checkbox" id="test"
												value="${item.id}"> </span>
										</div>
									</td>
									<td>
										<a href="/animal/animalsIndex?id=${item.id }">${item.id}</a>
									</td>
									<td>${item.animalsName}</td>
									<td>${item.type.typeName}</td>
                                    <td>${item.orderType}</td>
                                    <td>${item.orderTypeOrderBy}</td>

									<td><a class="btn default btn-xs blue-stripe" href="#"
										name="edit" id="${item.id}"><i
											class="fa fa-edit"></i> Edit</a> <a
										class="btn default btn-xs purple-stripe" href="#"
										name="delete" id="${item.id}"><i
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


	<input type="hidden" id="setOptionUrl" value="${ctx}/animal/" />
	
	<input type="hidden" id="animals_delete"/>

	<input type="hidden" id="ctxUrl" value="${ctx}" />
	<!-- PAGE LEVEL JS REFERENCES -->
	<script src="${ctx}/resources/scripts/pages/animal/animalList.js"
		type="text/javascript"></script>

    <script type="application/javascript">
        $("#orderType option[value=${seed.filter['orderType']}]").attr("selected",true);
    </script>
</body>