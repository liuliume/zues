package com.liuliume.portal.controller;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuliume.portal.common.JData;
import com.liuliume.portal.service.TestService;

@Controller
@RequestMapping(value = { "/test" }, method = {RequestMethod.GET,RequestMethod.POST})
public class TestController {

	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	private JData test(String name,String passwd){
		logger.info("Test");
		System.out.println("name:"+name);
		System.out.println("passwd:"+passwd);
		JData jData = new JData();
		try {
			testService.add(name, passwd);
			jData.setDetail("add success");
			jData.setSuccess(true);
		} catch (Exception e) {
			logger.error(
					"error! Error:{}, Parameter:name:{},passwd:{}",
					e.getMessage(), name,passwd, e);
			jData.setDetail("add fail");
			jData.setSuccess(false);
		}
		return jData;
	}
}
