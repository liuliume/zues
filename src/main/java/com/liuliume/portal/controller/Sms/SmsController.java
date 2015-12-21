package com.liuliume.portal.controller.Sms;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.liuliume.common.util.MD5Util;
import com.liuliume.common.util.ServletUtil;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: lzy_clement
 * Date: 15-9-10
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value={"/sms"})
public class SmsController {

    private Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    public static void main(String args[]){
//        CCPRestSmsSDK smsSDK = new CCPRestSmsSDK();
//        smsSDK.init("https://sandboxapp.cloopen.com","8883");
//        smsSDK.setAccount("8a48b5514fb1a66a014fb69391600c0f","8b3a07ae22e847d396c1d6cdc3458006");
//        smsSDK.setAppId("8a48b5514fb1a66a014fb693c1a00c11");
//        HashMap map = null;
//        map = smsSDK.sendTemplateSMS("13811633448","模板Id" ,new String[]{"模板内容1","模板内容2"});
//        System.out.println("SDKTestGetSubAccounts result=" + map);
//
        HashMap<String, Object> result = null;

        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init("app.cloopen.com","8883");

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
        restAPI.setAppId("8a48b551516c09cd01518470cac02d4d");


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
        result = restAPI.sendTemplateSMS("13811633448","1" ,new String[]{"6532","5"});

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }

    @RequestMapping(value="getMsgCode",method= RequestMethod.GET)
    @ResponseBody
    public JData getMsgCode(@RequestParam(value="mobile",required=true)String mobile){
        JData jData = new JData();
        try {
            boolean flag = smsService.getMsgCode(mobile);
            if(flag){
                jData.setData(flag);
                jData.setCode(200);
                jData.setSuccess(true);
                jData.setDetail("发送短信成功");
            } else {
                jData.setData(flag);
                jData.setCode(200);
                jData.setSuccess(true);
                jData.setDetail("发送短信失败");
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), "", e));
            jData.setData(null);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("发送短信异常");
        }
        return jData;
    }

    @RequestMapping(value="verifyMsgCode",method= RequestMethod.GET)
    @ResponseBody
    public JData verifyMsgCode(@RequestParam(value="mobile",required=true)String mobile,
                               @RequestParam(value="code",required=true)String code,HttpServletRequest request,HttpServletResponse response){
        JData jData = new JData();
        try {
            boolean flag = smsService.verifyMsgCode(mobile,code);
//            boolean flag = true;
            if(flag){
//                jData.setData(flag);
                jData.setCode(200);
                jData.setSuccess(true);
                jData.setDetail("验证成功");
                String sgid = MD5Util.MD5WithSalt(mobile);
                ServletUtil.setCookie(request,response,"sgid",sgid,6 * 30 * 24 * 60 * 60);
                ServletUtil.setCookie(request,response,"mobile",mobile,6 * 30 * 24 * 60 * 60);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("flag",flag);
                map.put("sgid",sgid);
                map.put("mobile",mobile);
                jData.setData(map);
            } else {
                jData.setData(flag);
                jData.setCode(200);
                jData.setSuccess(true);
                jData.setDetail("验证失败");
            }

        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), "", e));
            jData.setData(null);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("验证服务异常");
        }
        return jData;
    }

}
