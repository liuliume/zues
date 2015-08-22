package com.liuliume.portal.exception.resolver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理类
 * @author xiayun
 *
 */
public class ExceptionResolverHandler implements HandlerExceptionResolver{

	private static Logger logger=LoggerFactory.getLogger(ExceptionResolverHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String action = request.getRequestURI();
		logger.error("访问action:"+action+"出现异常:"+ex.getMessage());
		if(request.getHeader("accept").indexOf("application/json")>-1||(request.getHeader("X-Requested-With")!=null&&request.getHeader("X-Requested-With").indexOf("XMLHttpRequest")>-1)){
			//异步请求,使用json格式返回
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.write("<script>system error</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			ModelAndView mav = new ModelAndView("/error/error");
//			mav.addObject("", attributeValue)
			mav.addObject("ex",ex);
			return mav;
		}
		return null;
	}

}
