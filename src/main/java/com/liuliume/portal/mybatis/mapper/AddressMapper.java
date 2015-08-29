package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    int count(@Param("param")Parameter parameter);

    public List<HashMap<String,Object>> list(@Param("param")Parameter parameter);
}