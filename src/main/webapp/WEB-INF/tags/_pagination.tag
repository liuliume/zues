<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-7 col-sm-12">
	<div class="dataTables_paginate paging_bootstrap">
		<ul class="pagination">
			<!-- first page -->
			<c:choose>
				<c:when test="${seed.hasPreviousPage}">
					<li data-lp="1" class="prev"><a
						href="${ctx}/${seed.actionPath}?${pageSize}&page=1${queryStr}${pageOrder}"><icon
								class="fa fa-angle-double-left"></icon></a></li>
				</c:when>
				<c:otherwise>
					<li data-lp="1" class="prev disabled"><a
						href="javascript:void(0);"><icon
								class="fa fa-angle-double-left"></icon></a></li>
				</c:otherwise>
			</c:choose>

			<!-- pre page -->
			<c:choose>
				<c:when test="${seed.hasPreviousPage}">
					<li data-lp="1" class="prev"><a
						href="${ctx}/${seed.actionPath}?${pageSize}&page=${seed.pageNumber-1}${queryStr}${pageOrder}"><icon
								class="fa fa-angle-left"></icon></a></li>
				</c:when>
				<c:otherwise>
					<li data-lp="1" class="prev disabled"><a
						href="javascript:void(0);"><icon class="fa fa-angle-left"></icon></a>
					</li>
				</c:otherwise>
			</c:choose>

			<c:set var="count" value="5"></c:set>
			<c:choose>
				<c:when test="${seed.pageNumber + 1 > count}">
					<c:forEach begin="1" end="${count-1}">
						<c:if test="${count>0}">
							<c:set var="cur" value="${seed.pageNumber + 1 - count}"></c:set>
							<li data-lp="${cur}"><a
								href="${ctx}/${seed.actionPath}?${pageSize}&page=${cur}${queryStr}${pageOrder}">${cur}</a>
							</li>
							<c:set var="count" value="${count - 1}"></c:set>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:if test="${seed.pageNumber > 1}">
						<c:forEach begin="1" end="${seed.pageNumber - 1}"
							varStatus="status">
							<li data-lp="${status.count}"><a
								href="${ctx}/${seed.actionPath}?${pageSize}&page=${status.count}${queryStr}${pageOrder}">${status.count}</a>
							</li>
						</c:forEach>
					</c:if>
				</c:otherwise>
			</c:choose>

			<!-- current page -->
			<li data-lp="${seed.pageNumber}" class="active"><a
				href="javascript:void(0);">${seed.pageNumber}</a></li>

			<c:set var="count" value="5"></c:set>
			<c:set var="_break" value="0"></c:set>
			<c:forEach begin="1" end="${count-1}" varStatus="status">
				<c:if test="${_break == '0'}">
					<c:set var="cur" value="${seed.pageNumber+status.count}"></c:set>
					<c:choose>
						<c:when test="${cur > seed.totalPages}">
							<c:set var="_break" value="1"></c:set>
						</c:when>
						<c:otherwise>
							<li data-lp="${cur}"><a
								href="${ctx}/${seed.actionPath}?${pageSize}&page=${cur}${queryStr}${pageOrder}">${cur}</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>

			<!-- next page -->
			<c:choose>
				<c:when test="${seed.pageNumber < seed.totalPages}">
					<li data-lp="${seed.totalPages}" class="next"><a
						href="${ctx}/${seed.actionPath}?${pageSize}&page=${seed.pageNumber+1}${queryStr}${pageOrder}">
							<icon class="fa fa-angle-right"></icon>
					</a></li>
				</c:when>
				<c:otherwise>
					<li data-lp="7" class="next disabled"><a
						href="javascript:void(0);"><icon class="fa fa-angle-right"></icon></a>
					</li>
				</c:otherwise>
			</c:choose>

			<!-- last page -->
			<c:choose>
				<c:when test="${seed.pageNumber < seed.totalPages}">
					<li data-lp="${seed.totalPages}" class="next"><a
						href="${ctx}/${seed.actionPath}?${pageSize}&page=${seed.totalPages}${queryStr}${pageOrder}"><icon
								class="fa fa-angle-double-right"></icon></a></li>
				</c:when>
				<c:otherwise>
					<li data-lp="7" class="next disabled"><a
						href="javascript:void(0);"> <icon
								class="fa fa-angle-double-right"></icon></a></li>
				</c:otherwise>
			</c:choose>

			<!-- jump page -->
			<c:if test="${1 != seed.totalPages}">
				<li data-lp="${seed.totalPages}" class="next"><input
					name="custompage" type="text"
					style="width: 42px; height: 32px; display: inline; border: 1px solid #ddd;"
					title="输入页数跳转." /></li>
			</c:if>
		</ul>
	</div>
</div>