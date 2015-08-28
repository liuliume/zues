package com.liuliume.portal.entity;

public class Room {
    private Integer id;

    private String roomName;

    private Double cost;

    private Double weixinDiscount;

    private Double 30Discount;

    private Double 90Discount;

    private Double 180Discount;

    private Integer roomNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getWeixinDiscount() {
        return weixinDiscount;
    }

    public void setWeixinDiscount(Double weixinDiscount) {
        this.weixinDiscount = weixinDiscount;
    }

    public Double get30Discount() {
        return 30Discount;
    }

    public void set30Discount(Double 30Discount) {
        this.30Discount = 30Discount;
    }

    public Double get90Discount() {
        return 90Discount;
    }

    public void set90Discount(Double 90Discount) {
        this.90Discount = 90Discount;
    }

    public Double get180Discount() {
        return 180Discount;
    }

    public void set180Discount(Double 180Discount) {
        this.180Discount = 180Discount;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
}