package com.liuliume.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONObject;

import com.liuliume.common.util.RedisUtils;
import com.liuliume.common.util.WechatUtil;
import com.liuliume.portal.common.Constants;

public class WechatAccessTokenTest {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		
		String access_token = WechatUtil.getAccess_token();
		System.out.println(access_token);
	}
}
