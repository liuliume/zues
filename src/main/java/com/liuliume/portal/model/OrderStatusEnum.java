package com.liuliume.portal.model;

/**
 * 订单状态枚举类
 * @author xiayun
 *
 */
public enum OrderStatusEnum {

	ORDERED(0,"下单"),
	SERVICING(1,"服务中"),
	COMPLETE(2,"已完成"),
	ASSESSED(3,"已评价");
	
	private int id;
	private String desc;
	
	private OrderStatusEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static OrderStatusEnum parse(Integer id){
		if(id==null)
			return null;
		for(OrderStatusEnum item:OrderStatusEnum.values()){
			if(item.getId()==id.intValue())
				return item;
		}
		return null;
	}
}
