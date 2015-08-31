package com.liuliume.portal.service;

import java.util.List;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.AnimalsType;

public interface AnimalService {

	List<AnimalsType> list(Seed<AnimalsType> seed) throws Exception;
	
	void batchDeleteAnimalType(String idsStr) throws Exception;
}
