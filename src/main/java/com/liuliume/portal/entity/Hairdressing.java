package com.liuliume.portal.entity;

public class Hairdressing {
    private Integer id;

    private String hairdressingName;

    private String hairdressingDescribe;

    private Double expense;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHairdressingName() {
        return hairdressingName;
    }

    public void setHairdressingName(String hairdressingName) {
        this.hairdressingName = hairdressingName == null ? null : hairdressingName.trim();
    }

    public String getHairdressingDescribe() {
        return hairdressingDescribe;
    }

    public void setHairdressingDescribe(String hairdressingDescribe) {
        this.hairdressingDescribe = hairdressingDescribe == null ? null : hairdressingDescribe.trim();
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}