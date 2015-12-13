package com.liuliume.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuliume.common.util.RedisUtils;
import com.liuliume.common.util.WechatUtil;
import com.liuliume.portal.model.wechat.Button;
import com.liuliume.portal.model.wechat.CommonButton;
import com.liuliume.portal.model.wechat.ComplexButton;
import com.liuliume.portal.model.wechat.ViewButton;
import com.liuliume.portal.model.wechat.WechatMenu;

public class WeChatMenuTest {

	private static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");
		
		WechatUtil.utils = app.getBean(RedisUtils.class);

		String access_token = WechatUtil.getAccess_token();
		menu_create_url=menu_create_url.replaceAll("ACCESS_TOKEN", access_token);
		
		WechatMenu menu = _createMenu();
		
		String jsonMenu = JSONObject.fromObject(menu).toString();
		
		System.out.println(jsonMenu);
		
		URL url = new URL(menu_create_url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		
		OutputStream os = httpURLConnection.getOutputStream();
		os.write(jsonMenu.getBytes("UTF-8"));
		os.close();
		
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
		System.out.println(jsonObject.toString());
		if(jsonObject!=null){
			if (0 != jsonObject.getInt("errcode")) {  
	            int result = jsonObject.getInt("errcode");  
	            System.out.println(String.format("创建菜单失败 errcode:%s errmsg:%s", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"))); 
	        }else{
	        	System.out.println("create successful");
	        }
		}
	}

	private static WechatMenu _createMenu(){
//		CommonButton btn11 = new CommonButton();  
//        btn11.setName("天气预报"); 
//        btn11.setType("click");  
//        btn11.setKey("11");  
//  
//        CommonButton btn12 = new CommonButton();  
//        btn12.setName("测试测试");  
//        btn12.setType("click");  
//        btn12.setKey("12");
//        
////        CommonButton btn2 = new CommonButton();
////        btn2.setName("点击下单");
////        btn2.setKey("http://www.baidu.com");
////        btn2.setType("view");
        
        ViewButton btn2 = new ViewButton();
        btn2.setName("点击下单");
        btn2.setType("view");
        btn2.setUrl("http://182.254.132.53/resources/front_views/index.html");
  
  
//        ComplexButton mainBtn1 = new ComplexButton();  
//        mainBtn1.setName("生活助手");  
//        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });  
 
        WechatMenu menu = new WechatMenu();
        menu.setButton(new Button[]{btn2});
//        menu.setButton(new Button[]{mainBtn1,btn2});
        
        return menu;
	}
}
