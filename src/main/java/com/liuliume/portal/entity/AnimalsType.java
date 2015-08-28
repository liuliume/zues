package com.liuliume.portal.entity;

public class AnimalsType {
    private Integer id;

    private String typeName;

    private Double expenseCoefficient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Double getExpenseCoefficient() {
        return expenseCoefficient;
    }

    public void setExpenseCoefficient(Double expenseCoefficient) {
        this.expenseCoefficient = expenseCoefficient;
    }
}