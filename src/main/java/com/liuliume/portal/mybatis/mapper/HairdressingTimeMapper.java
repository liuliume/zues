package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HairdressingTimeMapper extends MyBatisBaseMapper<HairdressingTime> {
    int deleteByPrimaryKey(Integer id);

    int insert(HairdressingTime record);

    int insertSelective(HairdressingTime record);

    HairdressingTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HairdressingTime record);

    int updateByPrimaryKey(HairdressingTime record);

    int count(@Param("param")Parameter parameter);

    public List<HairdressingTime> list(@Param("param")Parameter parameter);
    
    public List<HairdressingTime> listAllHairdressingTime();

}