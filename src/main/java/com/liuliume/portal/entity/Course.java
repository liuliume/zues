package com.liuliume.portal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="course")
public class Course implements Serializable {
    private Integer id;

    private String courseName;

    private String courseDescribe;

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

    @Column(name="course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    @Column(name="course_describe")
    public String getCourseDescribe() {
        return courseDescribe;
    }

    public void setCourseDescribe(String courseDescribe) {
        this.courseDescribe = courseDescribe == null ? null : courseDescribe.trim();
    }

    @Column(name="expense")
    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}