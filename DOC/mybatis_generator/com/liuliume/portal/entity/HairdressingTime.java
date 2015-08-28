package com.liuliume.portal.entity;

public class HairdressingTime {
    private Integer id;

    private String startTime;

    private String endTime;

    private Integer servicePersionNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Integer getServicePersionNum() {
        return servicePersionNum;
    }

    public void setServicePersionNum(Integer servicePersionNum) {
        this.servicePersionNum = servicePersionNum;
    }
}