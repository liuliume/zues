package com.liuliume.portal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="hairdressing_time")
public class HairdressingTime implements Serializable {
    private Integer id;

    private String startTime;

    private String endTime;

    private Integer servicePersionNum;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    @Column(name="end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    @Column(name="service_persion_num")
    public Integer getServicePersionNum() {
        return servicePersionNum;
    }

    public void setServicePersionNum(Integer servicePersionNum) {
        this.servicePersionNum = servicePersionNum;
    }
}