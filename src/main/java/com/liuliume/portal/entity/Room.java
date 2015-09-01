package com.liuliume.portal.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="room")
public class Room implements Serializable {

    private Integer id;

    private String roomName;

    private Double cost;

    private Double weixinDiscount;

    private Double discount30;

    private Double discount90;

    private Double discount180;

    private Integer roomNum;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="room_name")
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    @Column(name="cost")
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Column(name="weixin_discount")
    public Double getWeixinDiscount() {
        return weixinDiscount;
    }

    public void setWeixinDiscount(Double weixinDiscount) {
        this.weixinDiscount = weixinDiscount;
    }

    @Column(name="discount_30")
    public Double getDiscount30() {
        return discount30;
    }

    public void setDiscount30(Double discount30) {
        this.discount30 = discount30;
    }

    @Column(name="discount_90")
    public Double getDiscount90() {
        return discount90;
    }

    public void setDiscount90(Double discount90) {
        this.discount90 = discount90;
    }

    @Column(name="discount_180")
    public Double getDiscount180() {
        return discount180;
    }

    public void setDiscount180(Double discount180) {
        this.discount180 = discount180;
    }

    @Column(name="room_num")
    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
}