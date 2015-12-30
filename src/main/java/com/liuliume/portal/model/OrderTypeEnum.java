package com.liuliume.portal.model;

/**
 * 订单类型枚举
 * @author xiayun
 *
 */
public enum OrderTypeEnum {
	
	ALL(-1,"ALL"),
	FOSTER(1,"宠物寄养"),
	TRAINING(2,"宠物训练"),
	BEAUTY(3,"宠物美容");
	
	private int id;
	private String desc;
	
	OrderTypeEnum(int id,String desc){
		this.id=id;
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
	
	public static OrderTypeEnum parse(int type_id) {
		for (OrderTypeEnum item : OrderTypeEnum.values()) {
			if(item.getId()==type_id)
				return item;
		}
		return null;
	}
}
