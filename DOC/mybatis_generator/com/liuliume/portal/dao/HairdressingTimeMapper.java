package com.liuliume.portal.dao;

import com.liuliume.portal.entity.HairdressingTime;

public interface HairdressingTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HairdressingTime record);

    int insertSelective(HairdressingTime record);

    HairdressingTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HairdressingTime record);

    int updateByPrimaryKey(HairdressingTime record);
}