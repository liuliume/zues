package com.liuliume.portal.controller.wechat;

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

	/**
     * 开发者模式token校验
     *
     * @param model
     * @throws Exception
     */
	@RequestMapping(value="validate",method=RequestMethod.GET,produces="text/plain")
	@ResponseBody
	public String validate(WechatCheckModel model) throws Exception {
		return wechatService.validate(model);
	}
}
