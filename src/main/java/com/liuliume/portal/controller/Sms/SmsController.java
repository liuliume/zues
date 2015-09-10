package com.liuliume.portal.controller.Sms;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lzy_clement
 * Date: 15-9-10
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
public class SmsController {

    public static void main(String args[]){
        CCPRestSmsSDK smsSDK = new CCPRestSmsSDK();
        smsSDK.init("https://sandboxapp.cloopen.com","8883");
        smsSDK.setAccount("accountSid","8a48b5514fb1a66a014fb69391600c0f");
        smsSDK.setAppId("8b3a07ae22e847d396c1d6cdc3458006");
        HashMap map = null;
        map = smsSDK.sendTemplateSMS("13811633448","模板Id" ,new String[]{"模板内容1","模板内容2"});
        System.out.println("SDKTestGetSubAccounts result=" + map);
    }
}
