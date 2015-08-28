package com.liuliume.portal.entity;

public class Room {
    private Integer id;

    private String roomName;

    private Double cost;

    private Double weixinDiscount;

    private Double discount30;

    private Double discount90;

    private Double discount180;

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

    public Double getDiscount30() {
        return discount30;
    }

    public void setDiscount30(Double discount30) {
        this.discount30 = discount30;
    }

    public Double getDiscount90() {
        return discount90;
    }

    public void setDiscount90(Double discount90) {
        this.discount90 = discount90;
    }

    public Double getDiscount180() {
        return discount180;
    }

    public void setDiscount180(Double discount180) {
        this.discount180 = discount180;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
}