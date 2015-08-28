package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Hairdressing;

public interface HairdressingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hairdressing record);

    int insertSelective(Hairdressing record);

    Hairdressing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hairdressing record);

    int updateByPrimaryKey(Hairdressing record);
}