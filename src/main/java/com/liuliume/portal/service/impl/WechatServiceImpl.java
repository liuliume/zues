package com.liuliume.portal.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.liuliume.common.util.HttpUtil;
import com.liuliume.common.util.JSONUtil;
import com.liuliume.common.util.MD5Util;
import com.liuliume.common.util.StringUtil;
import com.liuliume.common.util.WechatUtil;
import com.liuliume.common.util.XMLUtil;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.model.wechat.WechatCheckModel;
import com.liuliume.portal.service.OrdersService;
import com.liuliume.portal.service.WechatService;

@Service
public class WechatServiceImpl implements WechatService {

	private Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);
	
	@Autowired
	private OrdersService ordersService;
	
	@Value("${isTest}")
	private boolean isTest;

	@Override
	public String validate(WechatCheckModel model) throws Exception {
		if (null == model) {
			return null;
		}
		// String token = WechatUtil.getAccess_token();
		String token = Constants.Token;

		String signature = model.getSignature();
		Long timestamp = model.getTimestamp();
		String echoStr = model.getEchostr();
		Long nonce = model.getNonce();
		logger.info("signature:{}", signature);
		logger.info("timestam:{}", timestamp);
		logger.info("echoStr:{}", echoStr);
		logger.info("nonce:{}", nonce);

		String[] arr = { token, timestamp.toString(), nonce.toString() };
		Arrays.sort(arr);
		String str = arr[0] + arr[1] + arr[2];
		logger.info("after sort:{}", str);

		String sha = DigestUtils.sha1Hex(str);

		logger.info(sha);
		if (sha.equalsIgnoreCase(signature)) {
			return echoStr;
		}

		return "error";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getUserOpenId(String code) throws Exception {
		String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		uri = uri.replace("APPID", Constants.APP_ID)
				.replace("SECRET", Constants.APP_SECRET).replace("CODE", code);
		logger.info("Get Access token uri:{}", uri);

		String data = HttpUtil.doGet(uri);
		logger.debug("debug_msg:getUserOpenId:{}",data);
//		JSONObject jsonObject = JSONObject.fromObject(data);
		Map<String, Object>	map = JSONUtil.fromJson(data, HashMap.class);
		if(MapUtils.isNotEmpty(map) && map.get("openid")!=null){
			return map.get("openid").toString();
		}
		return null;
	}

	@Override
	public String prepay(HttpServletRequest request,String openId) throws Exception {
		// 使用TreeMap,插入时自动按照Key升序排序
		Map<String, String> paraMap = new TreeMap<String, String>();
		
		String nonce_str = WechatUtil.getNonceStr();

		String order_id = request.getParameter("state");
		Orders orders = ordersService.findOrdersByOrderId(order_id);
		String order_type = orders.getOrderTypeEnum().getDesc();
//		order_type = new String(order_type.getBytes(),"GBK");
		String total_fee = "1";
		if(!isTest){
			total_fee = String.valueOf((int)(orders.getCost()*100));
		}

		paraMap.put("appid", Constants.APP_ID);
		paraMap.put("mch_id", Constants.MCH_ID);
		paraMap.put("nonce_str", nonce_str);
		paraMap.put("body", order_type);
		paraMap.put("out_trade_no", order_id);
		paraMap.put("total_fee", total_fee);
		paraMap.put("spbill_create_ip", request.getRemoteAddr());
		paraMap.put("notify_url", Constants.NOTIFY_URL);
		paraMap.put("trade_type", "JSAPI");
		paraMap.put("openid", openId);

		String para = StringUtil.createParam(paraMap);
		para += "&key=" + Constants.PARTNER_KEY;
		para = new String(para.getBytes("utf-8"),"utf-8");
		logger.info("param:{}", para);
//		String sign = MD5Util.MD5(para).toUpperCase();
		String sign = DigestUtils.md5Hex(para).toUpperCase();
		paraMap.put("sign", sign);

		// 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String result = HttpUtil.doPost(url,XMLUtil.map2Xml(paraMap));
		logger.info("result:{}", result);
		// 预付商品id
		String prepay_id = "";
		if (result.indexOf("SUCCESS") != -1) {
			Map<String, Object> map = XMLUtil.xml2Map(result);
			prepay_id = map.get("prepay_id").toString();
		}
		return prepay_id;
	}

}
