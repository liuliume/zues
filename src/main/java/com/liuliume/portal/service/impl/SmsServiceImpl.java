package com.liuliume.portal.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.liuliume.common.util.JSONUtil;
import com.liuliume.common.util.RedisUtils;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.dao.AccountDao;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.service.SmsService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by clement on 9/10/15.
 */
@Service
public class SmsServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AccountDao accountDao;

    @Override
    public boolean getMsgCode(String mobile) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4 ; i++){
            sb.append(new Random().nextInt(9));
        }
//        Map<String,Object> result = sendMsg(mobile,sb.toString());
//        if("000000".equals(result.get("statusCode"))){
//            //正常返回输出data包体信息（map）
//            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for(String key:keySet){
//                Object object = data.get(key);
//                System.out.println(key +" = "+object);
//            }
//            redisUtils.setWithinSeconds(mobile + "_verifyNo", sb.toString(), 60*5);
//            return true;
//        }else{
//            //异常返回输出错误码和错误信息
//            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//            return false;
//        }
//        JSONObject json = sendMsgByNew(mobile,sb.toString());
        String json = sendMsgByNew(mobile,sb.toString());
        Result result = JSONUtil.fromJson(json,Result.class);
//        System.out.println(result);
        if(null != result && "0".equalsIgnoreCase(result.getCode())){
            redisUtils.setWithinSeconds(mobile + "_verifyNo", sb.toString(), 60*5);
            return true;
        }
//        redisUtils.setWithinSeconds(mobile + "_verifyNo", sb.toString(), 60*5);
        logger.error(json);
//        if(null != json && "0".equalsIgnoreCase(json.get("code").toString())){
//            redisUtils.setWithinSeconds(mobile + "_verifyNo", sb.toString(), 60*5);
//            return true;
//        }
        return false;
    }

    public boolean verifyMsgCode(String mobile,String code) {
        String redisCode = redisUtils.get(mobile + "_verifyNo");
        if(StringUtils.isNotEmpty(code) && code.equals(redisCode)) {

            Account tmp = accountDao.findAccountByMobile(mobile);
            if(null == tmp) {
                Account account = new Account();
                account.setMobile(mobile);
                accountDao.createAccount(account);
            }
            redisUtils.delete(mobile + "_verifyNo");
            return true;

        } else {
            return false;
        }
    }

    private String sendMsgByNew(String mobile,String verifyNo){
        String url = "https://sms.yunpian.com/v1/sms/send.json";
        Map<String,String> params = Maps.newHashMap();
        params.put("apikey","587eb48f44f05d613a88f9e4d4dc7d29");
        params.put("mobile",mobile);
        params.put("text","【汪族墅屋】您的验证码是 " + verifyNo + "，欢迎您的加入。");

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost post = postForm(url, params);
        String body = invoke(httpclient, post);
        httpclient.getConnectionManager().shutdown();
        if(null != body && StringUtils.isNotEmpty(body)){
            return body;
//            JSONObject json = JSONObject.fromObject(body);
//            return json;
        }
        return null;
    }


    private Map<String,Object> sendMsg(String mobile,String verifyNo) {
        HashMap<String, Object> result = null;
        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init("app.cloopen.com", "8883");
        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount("aaf98f894fb6c148014fb78d89b50126", "af963deb9e4b403d92b99cbb879fa3b0");
        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId("aaf98f8951af2ba80151cda1ed980570");
        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。																														       *
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
//        result = restAPI.sendTemplateSMS(mobile,"1" ,new String[]{"6532","5"});
        result = restAPI.sendTemplateSMS(mobile,"57617" ,new String[]{verifyNo});

        return result;

//        System.out.println("SDKTestGetSubAccounts result=" + result);
//        if("000000".equals(result.get("statusCode"))){
//            //正常返回输出data包体信息（map）
//            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for(String key:keySet){
//                Object object = data.get(key);
//                System.out.println(key +" = "+object);
//            }
//        }else{
//            //异常返回输出错误码和错误信息
//            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//        }
    }


    private static String paseResponse(HttpResponse response) {
//        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();

//        log.info("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
//        log.info(charset);

        String body = null;
        try {
            body = EntityUtils.toString(entity);
//            log.info(body);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

    private static String invoke(DefaultHttpClient httpclient,
                                 HttpUriRequest httpost) {

        HttpResponse response = sendRequest(httpclient, httpost);
        String body = paseResponse(response);

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient,
                                            HttpUriRequest httpost) {
//        log.info("execute post...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpPost postForm(String url, Map<String, String> params){

        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        Set<String> keySet = params.keySet();
        for(String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }

        try {
//            log.info("set utf-8 form entity to httppost");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpost;
    }
}

class Result {

    private String code;

    private String msg;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
