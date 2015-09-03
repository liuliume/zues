package com.liuliume.portal.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;

public interface AnimalsMapper extends MyBatisBaseMapper<Animals>{
	
	int count(@Param("param") Parameter parameter);
	
	List<Animals> listAnimals(@Param("param")Parameter parameter);
	
	Animals findAnimalsById(@Param("id")Integer id);
	
	List<Animals> listAllAnimals();
}