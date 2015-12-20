package com.liuliume.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static String doGet(String uri) throws Exception {
		HttpGet httpGet = new HttpGet(uri);
		HttpClient client = null;
		Builder customReqConf = RequestConfig.custom();
		httpGet.setConfig(customReqConf.build());
		HttpResponse res = null;
		client = WechatUtil.createSSLInsecureClient();
		res = client.execute(httpGet);
		InputStream is = res.getEntity().getContent();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(isr);

		String str = "";
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		isr.close();
		// 释放资源
		is.close();
		is = null;

		// JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		// System.out.println(jsonObject);
		System.out.println(buffer.toString());
		return buffer.toString();
	}

	public static String doPost(String url,String ...data) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		HttpClient client = null;
		Builder customReqConf = RequestConfig.custom();
		httpPost.setConfig(customReqConf.build());
		for (int i = 0; i < data.length; i++) {
			 StringEntity postEntity = new StringEntity(data[i], "UTF-8");
			 httpPost.setEntity(postEntity);
		}
		httpPost.addHeader("Content-Type", "text/xml");
		
		HttpResponse res = null;
		client = WechatUtil.createSSLInsecureClient();
		res = client.execute(httpPost);
		InputStream is = res.getEntity().getContent();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(isr);
		String str = "";
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		isr.close();
		// 释放资源
		is.close();
		is = null;
		
		System.out.println(buffer.toString());
		return buffer.toString();
	}
}
