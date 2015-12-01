package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;
import com.liuliume.portal.mybatis.Parameter;

import java.util.List;

public interface HairdressingTimeDao {


    public int count(Parameter parameter);

    public List<HairdressingTime> list(Parameter parameter);

    public HairdressingTime findHairdressingTimeById(Integer hairdressingTime_id);

    public void createHairdressingTime(HairdressingTime hairdressingTime);

    public void updateHairdressingTime(HairdressingTime hairdressingTime);

    public void delete(HairdressingTime hairdressingTime);
    
    public List<HairdressingTime> listAllHairdressingTime();
    
    public HairdressingTime getHairdressingTimeByStartTime(String startTime);
    
    public HairdressingTime getHairdressingTimeByStartTime(int startTime);

}
