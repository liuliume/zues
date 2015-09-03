package com.liuliume.portal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="hairdressing")
public class Hairdressing implements Serializable {
    private Integer id;

    private String hairdressingName;

    private String hairdressingDescribe;

    private Double expense;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="hairdressing_name")
    public String getHairdressingName() {
        return hairdressingName;
    }

    public void setHairdressingName(String hairdressingName) {
        this.hairdressingName = hairdressingName == null ? null : hairdressingName.trim();
    }

    @Column(name="hairdressing_describe")
    public String getHairdressingDescribe() {
        return hairdressingDescribe;
    }

    public void setHairdressingDescribe(String hairdressingDescribe) {
        this.hairdressingDescribe = hairdressingDescribe == null ? null : hairdressingDescribe.trim();
    }

    @Column(name="expense")
    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}