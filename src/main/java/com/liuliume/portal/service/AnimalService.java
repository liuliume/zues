package com.liuliume.portal.service;

import java.util.List;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.AnimalsType;

public interface AnimalService {

	List<AnimalsType> list(Seed<AnimalsType> seed) throws Exception;
	
	/**
	 * 批量删除宠物类型(大/中/小)
	 * @param idsStr 用英文逗号分隔
	 * @throws Exception
	 */
	void batchDeleteAnimalType(String idsStr) throws Exception;
	
	void createOrUpdateType(AnimalsType type) throws Exception;
	
	AnimalsType findAnimalsTypeById(Integer id);
	
	List<AnimalsType> listAllTypes();
	
	
	List<Animals> listAnimals(Seed<Animals> seed) throws Exception;
	
	/**
	 * 批量删除宠物种类(加菲/牧羊犬)
	 * @param idsStr 以英文逗号分隔
	 * @throws Exception
	 */
	void batchDeleteAnimals(String idsStr) throws Exception;
	
	void createOrUpdateAnimals(Animals animals) throws Exception;
	
	Animals findAnimalsById(Integer id);
}
