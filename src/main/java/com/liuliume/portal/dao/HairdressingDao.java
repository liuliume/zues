package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.mybatis.Parameter;

import java.util.List;

public interface HairdressingDao {


    public int count(Parameter parameter);

    public List<Hairdressing> list(Parameter parameter);

    public Hairdressing findHairdressingById(Integer hairdress_id);

    public void createHairdressing(Hairdressing hairdressing);

    public void updateHairdressing(Hairdressing hairdressing);

    public void delete(Hairdressing hairdressing);

}
