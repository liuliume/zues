package com.liuliume.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/wechat"})
public class WeChatController {

	private Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	@RequestMapping(value="urltoken",method=RequestMethod.GET)
	@ResponseBody
	public void valid(HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		logger.info("");
	}
}
