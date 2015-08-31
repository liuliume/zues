package com.liuliume.portal.dao;

import java.util.List;

import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.Parameter;

public interface AnimalDao {

	int countAnimalsType(Parameter parameter);
	
	public List<AnimalsType> listAnimalsType(Parameter parameter);
	
	void deleteAnimalType(AnimalsType type);
}
