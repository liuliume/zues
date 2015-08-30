package com.liuliume.portal.model;

/**
 * Created by clement on 8/30/15.
 */
public enum AddressLevelEnum {

    First(null,"一级地址"),
    Second(1,"二级地址"),
    Third(2,"三级地址");

    private Integer level;

    private String desc;

    private AddressLevelEnum(Integer level,String desc){
        this.level = level;
        this.desc = desc;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static AddressLevelEnum parse(int level){
        for (AddressLevelEnum item : AddressLevelEnum.values()) {
            if(item.getLevel() == level)
                return item;
        }
        return null;
    }
}
