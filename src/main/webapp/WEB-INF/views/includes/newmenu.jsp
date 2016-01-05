<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="liuliume" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.liuliume.portal.common.Constants,com.liuliume.portal.common.Admins" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="header navbar-inner page-header-fixed">
    <div class="navbar-collapse collapse">
    	<ul class="nav navbar-nav">
	   	    <li>
		    	<a class="navbar-brand" href="/index">
					<span style="color:#fff;">LiuLiume Platform</span>
				</a>
			</li>
		</ul>
        <ul class="nav navbar-nav">
	        <c:forEach items="${menus}" var="menu">
                <c:if test="${menu.children!=null}" var="childrenif">
                    <li class="dropdown">
                        <a class="dropdown-toggle" role="button" data-toggle="dropdown" data-target="#" href="#">${menu.name}<i class="arrowDown"></i></a>
                        <ul class="dropdown-menu" role="menu">
                            <c:forEach items="${menu.children}" var="childMenu">
                                <li><a href="${ctx}${childMenu.url}">${childMenu.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${!childrenif}">
                    <li>
                        <a href="${ctx}${menu.url}">${menu.name}</a>
                    </li>
                </c:if>

	        </c:forEach>
        </ul>
        <ul class="nav navbar-nav pull-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" data-hover="dropdown"
				data-close-others="true"><i class="fa fa-user"></i><span
					class="username" title="Demo用户">
					<%
					    String userName = "Demo用户";
						if(session.getAttribute(Constants.SESSION_ADMIN)!=null){
							userName = ((Admins)session.getAttribute(Constants.SESSION_ADMIN)).getAdmin_name();
						}
						if(userName.length() > 20){
							out.println(userName.substring(0, 20) + "...");
						}else{
							out.println(userName);
						}
					%>
					</span> <i
					class="fa fa-angle-down"></i></a>
				<ul class="dropdown-menu">
				<li><a href="${ctx}/resources/supplierHelper.doc"><i class="fa fa-question-circle"></i> Help</a></li>
				<li><a href="${ctx}/logout"><i class="fa fa-key"></i> Log Out</a></li>
				</ul>
			</li>
		</ul>
   </div>
  </div>