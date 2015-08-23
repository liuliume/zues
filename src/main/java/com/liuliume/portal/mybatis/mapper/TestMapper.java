package com.liuliume.portal.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.liuliume.portal.mybatis.MyBatisBaseMapper;

public interface TestMapper{
	
	void add(@Param("name")String name,@Param("passwd")String passwd);
}
