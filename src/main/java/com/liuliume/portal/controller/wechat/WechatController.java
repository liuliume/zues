package com.liuliume.portal.controller.wechat;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liuliume.common.util.HttpUtil;
import com.liuliume.common.util.JSONUtil;
import com.liuliume.common.util.MD5Util;
import com.liuliume.common.util.StringUtil;
import com.liuliume.common.util.WechatUtil;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.model.wechat.WechatCheckModel;
import com.liuliume.portal.service.WechatService;

@Controller
@RequestMapping(value="wechat",method=RequestMethod.GET)
public class WechatController {
	
	@Autowired
	private WechatService wechatService;

	private static Logger logger = LoggerFactory.getLogger(WechatController.class);
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
	
	@RequestMapping(value="wxOauth",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String wxOauth(HttpServletRequest request,HttpServletResponse response,String order_id) throws Exception{
		logger.info("Get Code begin.");
		String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI"
				+ "&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		String redirect_uri = "http://www.liuliume.com/wechat/wxPrepay";
		redirect_uri = java.net.URLEncoder.encode(redirect_uri,"utf-8");
		System.out.println(redirect_uri);
		logger.info("redirect_url:"+redirect_uri);
		//将Order_Id放在state中
		oauth2Url = oauth2Url.replace("APPID", Constants.APP_ID)
				.replace("REDIRECT_URI", redirect_uri)
				.replace("SCOPE", Constants.SCOPE)
				.replace("STATE", order_id);

		System.out.println("GETCODE URL:" + oauth2Url);

//		response.sendRedirect(oauth2Url);
		return oauth2Url;
	}
	
	@RequestMapping(value="wxPrepay",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView wxPrepay(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String code = request.getParameter("code");
		String openId = "";
		if (StringUtils.isBlank(code)) {
			openId = request.getParameter("openId");
		} else {
			//获取用户ID
			logger.debug("debug_msg:code:{}",code);
			System.out.println(code);
			openId = wechatService.getUserOpenId(code);
		}

		System.out.println("debug_msg:open_id:"+openId);
		logger.debug("debug_msg:open_id:{}",openId);
		//订单ID通过STATE传入
		String order_id = request.getParameter("state");
		
		String prepay_id = wechatService.prepay(request,openId);
		
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		Map<String, String> map = new TreeMap<String, String>();
		map.put("appId", Constants.APP_ID);
		map.put("timeStamp", timestamp);
		map.put("nonceStr", WechatUtil.getNonceStr());
		map.put("package", "prepay_id="+prepay_id);
		map.put("signType", "MD5");
		
		String para = StringUtil.createParam(map);
		para += "&key=" + Constants.PARTNER_KEY;
		logger.info("param:{}", para);
		String sign = MD5Util.MD5(para).toUpperCase();
		map.put("paySign", sign);
		
		logger.info(map.toString());
		String result=JSONUtil.toJson(map);
		ModelAndView mav = new ModelAndView("/wechat/pay");
		mav.addObject("orderId",order_id);
		mav.addObject("result", result);
		return mav;
	}
	
	public static void main(String[] args) {
		String prepay_id = WechatUtil.getNonceStr();
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new TreeMap<String, String>();
		map.put("appId", Constants.APP_ID);
		map.put("timeStamp", timestamp);
		map.put("nonceStr", WechatUtil.getNonceStr());
		map.put("package", "prepay_id="+prepay_id);
		map.put("signType", "MD5");
		
		String para = StringUtil.createParam(map);
		para += "&key=" + Constants.PARTNER_KEY;
		logger.info("param:{}", para);
		String sign = MD5Util.MD5(para).toUpperCase();
		map.put("paySign", sign);
		
		jsonObject = JSONObject.fromObject(map);
		logger.info(jsonObject.toString());
	}
}
