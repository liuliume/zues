package com.liuliume.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuliume.portal.common.Constants;

import net.sf.json.JSONObject;

public class WechatUtil {
	
	@Autowired
	public static RedisUtils utils;

	private static JSONObject _getAccessToken() throws Exception {

		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ Constants.APP_ID + "&secret=" + Constants.APP_SECRET;
		String accessToken = "";
		
		HttpClient client = null;
		HttpGet httpGet = new HttpGet(accessTokenUrl);
		String result="";
		try {
			Builder customReqConf = RequestConfig.custom();
			httpGet.setConfig(customReqConf.build());
			HttpResponse res = null; 
			client = createSSLInsecureClient();  
            res = client.execute(httpGet); 
            InputStream is = res.getEntity().getContent();
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
    		
    		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
    		
    		if(jsonObject!=null){
    			accessToken = jsonObject.getString("access_token");
    			Integer expires_in = jsonObject.getInt("expires_in");
    			utils.setWithinSeconds("access_token", accessToken, expires_in);
    			System.out.println(accessToken);
    			System.out.println(expires_in);
    		}
    		return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getAccess_token() throws Exception{
		String access_token = utils.get("access_token");
		if(StringUtils.isBlank(access_token)){
			JSONObject jsonObject = _getAccessToken();
			access_token = jsonObject.getString("access_token");
		}
		return access_token;
	}
	
	public static String getNonceStr() {
		String nonceString=MD5Util.MD5(String.valueOf(new Random().nextInt(10000)));
		return nonceString;
	}
	
	public static CloseableHttpClient createSSLInsecureClient()  
            throws GeneralSecurityException {  
        try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(  
                    null, new TrustStrategy() {  
                        public boolean isTrusted(X509Certificate[] chain,  
                                String authType) throws CertificateException {  
                            return true;  
                        }  
                    }).build();  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                    sslContext, new X509HostnameVerifier() {

						@Override
						public boolean verify(String hostname,
								SSLSession session) {
							// TODO Auto-generated method stub
							return true; 
						}

						@Override
						public void verify(String arg0, SSLSocket arg1)
								throws IOException {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void verify(String arg0, X509Certificate arg1)
								throws SSLException {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void verify(String arg0, String[] arg1,
								String[] arg2) throws SSLException {
							// TODO Auto-generated method stub
							
						}  
  
                    });  
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();  
        } catch (GeneralSecurityException e) {  
            throw e;  
        }  
    }  
}
