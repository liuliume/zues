package com.liuliume.portal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuliume.common.util.RedisUtils;
import com.liuliume.portal.common.Constants;

import net.sf.json.JSONObject;

public class WechatUtil {
	
	@Autowired
	private static RedisUtils utils;

	private static JSONObject _getAccessToken() throws Exception {

		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ Constants.APP_ID + "&secret=" + Constants.APP_SECRET;
		String accessToken = "";
		URL url = new URL(accessTokenUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		
		httpURLConnection.connect();
		InputStream is = httpURLConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(isr);
		
		String str="";
		StringBuffer buffer=new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		isr.close();
		// 释放资源
		is.close();
		is = null;
		httpURLConnection.disconnect();
		
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		
		if(jsonObject!=null){
			accessToken = jsonObject.getString("access_token");
			Integer expires_in = jsonObject.getInt("expires_in");
			utils.setWithinSeconds("access_token", accessToken, expires_in);
			System.out.println(accessToken);
			System.out.println(expires_in);
		}
		return jsonObject;
	}
	
	public static String getAccess_token() throws Exception{
		if(utils==null)
			utils = new RedisUtils();
		String access_token = utils.get("access_token");
		if(StringUtils.isBlank(access_token)){
			JSONObject jsonObject = _getAccessToken();
			access_token = jsonObject.getString("access_token");
		}
		return access_token;
	}
}
