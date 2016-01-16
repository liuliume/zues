package com.liuliume.portal.common;

public class Constants {
	// 后台系统Session key
	public static final String SESSION_ADMIN = "SESSION_ADMIN";
	public static final String SESSION_AUTHORIZED_ACTION = "SESSION_AUTHORIZED_ACTION";
	public static final String SESSION_FILTERED_MENU = "SESSION_FILTERED_MENU";

	// 前端普通用户Session Key
	public static final String SESSION_USER = "SESSION_USER";

	public static final int SIMPLE_DELETE = 99;

	// 地址级别
	public static final Integer LEVEL_NATION = 0;// 国家级别
	public static final Integer LEVEL_PROVINCE = 1;
	public static final Integer LEVEL_CITY = 2;
	public static final Integer LEVEL_AREA = 3;

	// 服务类型Service Type
	public static final int SERVICE_DOOR = 0;// 上门服务
	public static final int SERVICE_SHOP = 1;// 到店服务

	// 付款状态
	public static final int PAYMENT_NO = 0;
	public static final int PAYMENT_YES = 1;

	// 付款方式
	public static final int PAYMENTTYPE_ONLINE = 1;// 在线付款
	public static final int PAYMENTTYPE_OFFLINE = 2;// 线下付款

	// 内存缓存时间
	public static final int CACHE_REFRESH_INTERVAL = 5;

	// public static final String APP_ID = "wx2d60b47537b993fa";
	// public static final String APP_SECRET="1e7dc4d6eaa531dc1e38c0543a66aa44";

	/**
	 * 微信APPID
	 */
//	public static final String APP_ID = "wx3382d54a842ce919";
	public static final String APP_ID = "wxe1887e6019fc8283";
	/**
	 * 微信APPSecret
	 */
//	public static final String APP_SECRET = "2885c1ad046baf1c8e4ae7451fe1acae";
	public static final String APP_SECRET = "b33ef098cb39f4cdfe249458eb49b1f3";
	/**
	 * 微信商户ID,身份标识
	 */
//	public static final String MCH_ID="1289131601";
	public static final String MCH_ID = "1307349501";
	/**
	 * 商户支付密钥Key。审核通过后，在微信发送的邮件中查看
	 */
	public static final String PARTNER_KEY ="1q2w3e4r5t6y7u8i9o0pazsxdcfvgbhn";
	/**
	 * 异步回调地址
	 */
	public static final String NOTIFY_URL="http://wangzupet.com/wechat/notify";
	/**
	 * 不弹出授权页面，仅仅能够获取用户的openId
	 * 如果使用snsapi_info，会弹出一个授权页面,可以获取用户的所有信息
	 */
	public static final String SCOPE = "snsapi_base";

	public static final String Token = "qwerasdfzxcv";
}
