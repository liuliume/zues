package com.liuliume.portal.common;

public class Constants {
	public static final String SESSION_ADMIN = "SESSION_ADMIN";
	public static final String SESSION_AUTHORIZED_ACTION = "SESSION_AUTHORIZED_ACTION";
	public static final String SESSION_FILTERED_MENU = "SESSION_FILTERED_MENU";
	
	public static final int SIMPLE_DELETE=99;
	
	//地址级别
	public static final int LEVEL_NATION = 0;//国家级别
	public static final int LEVEL_PROVINCE=1;
	public static final int LEVEL_CITY=2;
	public static final int LEVEL_AREA=3;
	
	//服务类型Service Type
	public static final int SERVICE_DOOR=0;//上门服务
	public static final int SERVICE_SHOP=1;//到店服务
	
	//付款状态
	public static final int PAYMENT_NO = 0;
	public static final int PAYMENT_YES=1;
	
	//付款方式
	public static final int PAYMENTTYPE_ONLINE = 1;//在线付款
	public static final int PAYMENTTYPE_OFFLINE=2;//线下付款

    //内存缓存时间
    public static final int CACHE_REFRESH_INTERVAL = 5;
}
