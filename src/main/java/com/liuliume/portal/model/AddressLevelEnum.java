package com.liuliume.portal.model;

/**
 * Created by clement on 8/30/15.
 */
public enum AddressLevelEnum {

    First("1","省"),
    Second("2","市"),
    Third("3","区域");

    private String level;

    private String desc;

    private AddressLevelEnum(String level,String desc){
        this.level = level;
        this.desc = desc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static AddressLevelEnum parse(String level){
        for (AddressLevelEnum item : AddressLevelEnum.values()) {
            if(item.getLevel().equalsIgnoreCase(level))
                return item;
        }
        return null;
    }
}
