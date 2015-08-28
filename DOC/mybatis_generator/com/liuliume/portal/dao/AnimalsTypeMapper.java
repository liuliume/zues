package com.liuliume.portal.dao;

import com.liuliume.portal.entity.AnimalsType;

public interface AnimalsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnimalsType record);

    int insertSelective(AnimalsType record);

    AnimalsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnimalsType record);

    int updateByPrimaryKey(AnimalsType record);
}