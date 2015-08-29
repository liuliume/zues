package com.liuliume.portal.model;

public enum GenderEnum {

	All(-1,"All"),
	MALE(0,"男"),
	FEMALE(1,"女");
	
	private int id;
	private String desc;
	
	private GenderEnum(int id,String desc) {
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
	
	public static GenderEnum parse(int id){
		for (GenderEnum item : GenderEnum.values()) {
			if(item.getId() == id)
				return item;
		}
		return null;
	}
}
