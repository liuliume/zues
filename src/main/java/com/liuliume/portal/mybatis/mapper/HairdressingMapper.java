package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HairdressingMapper extends MyBatisBaseMapper<Hairdressing> {
    int deleteByPrimaryKey(Integer id);

    int insert(Hairdressing record);

    int insertSelective(Hairdressing record);

    Hairdressing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hairdressing record);

    int updateByPrimaryKey(Hairdressing record);

    int count(@Param("param")Parameter parameter);

    public List<Hairdressing> list(@Param("param")Parameter parameter);
    
    public List<Hairdressing> listAllHairdressings();

}