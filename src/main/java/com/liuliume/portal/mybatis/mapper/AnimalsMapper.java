package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Animals;

public interface AnimalsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Animals record);

    int insertSelective(Animals record);

    Animals selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Animals record);

    int updateByPrimaryKey(Animals record);
}