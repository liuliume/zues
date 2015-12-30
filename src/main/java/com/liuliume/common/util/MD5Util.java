package com.liuliume.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: lzy_clement
 * Date: 15-9-16
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public class MD5Util {

    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static String MD5WithSalt(String s,String salt) {
        char hexDigits[] = { '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = (s+salt).getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static String MD5WithSalt(String s) {
        return MD5WithSalt(s,"564213879");
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String s=     "appid=wx3382d54a842ce919&body=宠物训练&mch_id=1289131601&nonce_str=CAA89215E67B35D504B99A0B22F1C56D&notify_url=http://www.liuliume.com/wechat/notify&openid=okj2mwAT2QpIChRdt4KEoRsoEVb4&out_trade_no=2jGdhIK41451479840156eADc&spbill_create_ip=127.0.0.1&total_fee=1&trade_type=JSAPI&key=1q2w3e4r5t6y7u8i9o0pazsxdcfvgbhn";
    	System.out.println(DigestUtils.md5Hex(s));
    	System.out.println(s.equals(new String(s.getBytes("utf-8"),"utf-8")));
        System.out.println(MD5Util.MD5(s));
        System.out.print(MD5Util.MD5WithSalt("passport","123"));
    }
}
