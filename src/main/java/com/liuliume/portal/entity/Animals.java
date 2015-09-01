package com.liuliume.portal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="animals")
public class Animals implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String animalsName;

    private Integer typeId;
    
    private AnimalsType type;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="animals_name")
    public String getAnimalsName() {
        return animalsName;
    }

    public void setAnimalsName(String animalsName) {
        this.animalsName = animalsName == null ? null : animalsName.trim();
    }

    @Column(name="type_id")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Transient
	public AnimalsType getType() {
		return type;
	}

	public void setType(AnimalsType type) {
		this.type = type;
	}
}