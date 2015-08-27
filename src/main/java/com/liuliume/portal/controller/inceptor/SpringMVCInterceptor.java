package com.liuliume.portal.controller.inceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.liuliume.portal.service.MenuService;


@Component("SpringMVCInterceptor")
public class SpringMVCInterceptor implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(SpringMVCInterceptor.class);

	@Autowired 
	private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO 权限控制
		String actionName= request.getRequestURI();
		logger.info("------用户访问:"+actionName);
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
