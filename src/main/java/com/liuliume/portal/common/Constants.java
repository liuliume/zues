package com.liuliume.portal.common;


public class Constants {
	//后台系统Session key
	public static final String SESSION_ADMIN = "SESSION_ADMIN";
	public static final String SESSION_AUTHORIZED_ACTION = "SESSION_AUTHORIZED_ACTION";
	public static final String SESSION_FILTERED_MENU = "SESSION_FILTERED_MENU";
	
	//前端普通用户Session Key
	public static final String SESSION_USER = "SESSION_USER"; 
	
	public static final int SIMPLE_DELETE=99;
	
	//地址级别
	public static final Integer LEVEL_NATION = 0;//国家级别
	public static final Integer LEVEL_PROVINCE=1;
	public static final Integer LEVEL_CITY=2;
	public static final Integer LEVEL_AREA=3;
	
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
    
//    public static final String APP_ID = "wx2d60b47537b993fa";
//    public static final String APP_SECRET="1e7dc4d6eaa531dc1e38c0543a66aa44";
    
  public static final String APP_ID = "wx3382d54a842ce919";
  public static final String APP_SECRET="2885c1ad046baf1c8e4ae7451fe1acae";

    
    public static final String Token = "qwerasdfzxcv";
}
