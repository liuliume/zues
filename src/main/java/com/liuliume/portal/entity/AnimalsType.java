package com.liuliume.portal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="animals_type")
public class AnimalsType implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String typeName;

    private Double expenseCoefficient;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    @Column(name="expense_coefficient")
    public Double getExpenseCoefficient() {
        return expenseCoefficient;
    }

    public void setExpenseCoefficient(Double expenseCoefficient) {
        this.expenseCoefficient = expenseCoefficient;
    }
}