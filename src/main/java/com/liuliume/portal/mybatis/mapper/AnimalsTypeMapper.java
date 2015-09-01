package com.liuliume.portal.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;

public interface AnimalsTypeMapper extends MyBatisBaseMapper<AnimalsType>{
    
    public int count(@Param("parameter")Parameter parameter);
    
    public List<AnimalsType> list(@Param("parameter")Parameter parameter);
    
    AnimalsType findAnimalsTypeById(@Param("id")Integer id);
    
    public List<AnimalsType> listAllTypes();
    
}