<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="liuliume" tagdir="/WEB-INF/tags"%>

<%@ attribute name="position" required="true" rtexprvalue="true"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:if test="${seed != null && !empty seed.result}">

	<c:set var="pageSize" value="pagesize=${seed.pagesize}" scope="request" />
	<c:set var="pageSizes" value="${fn:split('10,25,50,100', ',')}"
		scope="request" />
	<c:set var="queryStr" value="${seed.queryStr}" scope="request" />

	<c:choose>
		<c:when test="${seed.pageOrder.name != ''}">
			<c:set var="pageOrder"
				value="&pageOrder.order=${seed.pageOrder.orderExpressions}"
				scope="request" />
		</c:when>
		<c:otherwise>
			<c:set var="pageOrder" value="" scope="request" />
		</c:otherwise>
	</c:choose>

	<div class="row">
		<c:choose>
			<c:when test="${position eq 'above' }">
				<div class="col-md-5 col-sm-12">
					<div class="dataTables_length">
						<label>显示 <select class="form-control input-xsmall"
							onchange="javascript:self.location.href='${ctx}/${seed.actionPath}?pagesize='+options[selectedIndex].value+'${queryStr}${pageOrder}'"><c:forEach
									var="pagesize" items="${pageSizes}">
									<option value="${pagesize}"
										<c:if test="${seed.pagesize eq pagesize}">selected="selected"</c:if>>${pagesize}</option>
								</c:forEach></select> 项结果
						</label>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-5 col-sm-12">
					<div class="dataTables_info">
						<c:choose>
							<c:when test="${seed.totalSize < seed.endPosition}">
								<label>显示第 ${seed.startPosition + 1} 至 ${seed.totalSize }
									项结果，共 ${seed.totalSize } 项</label>
							</c:when>
							<c:otherwise>
								<label>显示第 ${seed.startPosition + 1} 至
									${seed.endPosition } 项结果，共 ${seed.totalSize } 项</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<liuliume:_pagination></liuliume:_pagination>
		<c:if test="${position eq 'below' && 1 != seed.totalPages}">
			<script type="text/javascript">
				 $('input[name=custompage]').each(function() {
			         $(this).unbind().keydown(function(ev) {
					     var e = ev ? ev : (window.event ? window.event : null);
					     if (e.keyCode == 13) {
					    	 var url = self.location.href;
					    	 var pageIndex = url.indexOf('page=');
					         if (pageIndex >= 0) {
					        	 var frontUrl = url.substr(0, pageIndex);
					        	 var tailUrl = url.substr(pageIndex);
					        	 if(tailUrl.indexOf("&") >= 0){
					        		 tailUrl = tailUrl.substr(tailUrl.indexOf("&"));
					        	 }else{
					        		 tailUrl = "";
					        	 }
					        	 url = frontUrl + "page=" + $(this).val() + tailUrl;
					         }else{
					        	 if(url.indexOf('?') == -1){
					        		 url += "?";
					        	 }else{
					        		 url += "&";
					        	 }
					        	 url +=("page=" + $(this).val());
					         }
					         window.location.href = url;
					         return false;
				      }
				    });
				  });
			</script>
		</c:if>
		<c:if test="${position eq 'below' && 0 != seed.totalSize}">
			<script type="text/javascript">
				$("th.sorting,th.sorting_desc,th.sorting_asc").bind("click", function(){
					window.location.href = $(this).attr("action");
				});
			</script>
		</c:if>
	</div>
</c:if>
<c:if
	test="${(seed == null || empty seed.result) && position eq 'below'}">
	<div class="row">
		<div class="col-md-12" style="text-align: center;">没有任何数据</div>
	</div>
</c:if>