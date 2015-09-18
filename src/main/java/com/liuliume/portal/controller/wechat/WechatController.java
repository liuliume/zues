package com.liuliume.portal.controller.wechat;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuliume.common.util.WechatUtil;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.model.wechat.WechatCheckModel;
import com.liuliume.portal.service.WechatService;

@Controller
@RequestMapping(value="wechat",method=RequestMethod.GET)
public class WechatController {
	
	@Autowired
	private WechatService wechatService;

	private Logger logger = LoggerFactory.getLogger(WechatController.class);
	/**
     * 开发者模式token校验
     *
     * @param model
     * @throws Exception
     */
	@RequestMapping(value="validate",method=RequestMethod.GET,produces="text/plain")
	public void validate(WechatCheckModel model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		logger.info("wechat valid begin");
		logger.info(model.toString());
		
		String ret = wechatService.validate(model);
		logger.info("ret:{}",ret);
		
		PrintWriter out = response.getWriter();
		out.print(ret);
		out.flush();
		out.close();
	}
}
