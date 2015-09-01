package com.liuliume.portal.dao;

import java.util.List;

import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.Parameter;

public interface AnimalDao {

	//animal type
	int countAnimalsType(Parameter parameter);
	
	public List<AnimalsType> listAnimalsType(Parameter parameter);
	
	void deleteAnimalType(AnimalsType type);
	
	void insertAnimalType(AnimalsType type);
	
	void updateAnimalType(AnimalsType type);
	
	AnimalsType findAnimalsTypeById(Integer id);
	
	public List<AnimalsType> listAllTypes();
	
	
	//animal categories
	int countAnimals(Parameter parameter);
	
	List<Animals> listAnimals(Parameter parameter);
	
	void deleteAnimals(Animals animals);
	
	void addAnimals(Animals animals);
	
	void editAnimals(Animals animals);
	
	Animals findAnimalsById(Integer id);
}
