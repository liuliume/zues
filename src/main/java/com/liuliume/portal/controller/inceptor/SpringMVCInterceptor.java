package com.liuliume.portal.controller.inceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.liuliume.portal.common.Admins;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.service.MenuService;

@Component("SpringMVCInterceptor")
public class SpringMVCInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(SpringMVCInterceptor.class);

	@Autowired
	private MenuService menuService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO 权限控制
		String actionName = request.getRequestURI();
		if (!actionName.startsWith("/resources")) {
			logger.info("------用户访问:" + actionName);
		}
		if (actionName.equalsIgnoreCase("/loginPage")
				|| actionName.startsWith("/resources")
				|| actionName.startsWith("/wechat"))
			return true;
		// ajax请求不判断用户是否登录
		String requestType = request.getHeader("X-Requested-With");
		if (StringUtils.isNotBlank(requestType)
				&& requestType.equalsIgnoreCase("XMLHttpRequest"))
			return true;
		HttpSession session = request.getSession();
		Admins admins = (Admins) session.getAttribute(Constants.SESSION_ADMIN);
		if (admins == null) {
			response.sendRedirect("/loginPage");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
