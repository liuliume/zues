package com.liuliume.portal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuliume.portal.dao.AnimalDao;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.AnimalsMapper;
import com.liuliume.portal.mybatis.mapper.AnimalsTypeMapper;

@Repository
public class AnimalDaoImpl implements AnimalDao{

	@Autowired
	private AnimalsTypeMapper typeMapper;
	
	@Autowired
	private AnimalsMapper animalsMapper;

	@Override
	public int countAnimalsType(Parameter parameter) {
		return typeMapper.count(parameter);
	}

	@Override
	public List<AnimalsType> listAnimalsType(Parameter parameter) {
		return typeMapper.list(parameter);
	}

	@Override
	public void deleteAnimalType(AnimalsType type) {
		typeMapper.deleteEntityPhysically(type);
	}

	@Override
	public void insertAnimalType(AnimalsType type) {
		typeMapper.createEntity(type);
	}

	@Override
	public void updateAnimalType(AnimalsType type) {
		typeMapper.updateEntity(type);
	}

	@Override
	public AnimalsType findAnimalsTypeById(Integer id) {
		return typeMapper.findAnimalsTypeById(id);
	}

	@Override
	public int countAnimals(Parameter parameter) {
		return animalsMapper.count(parameter);
	}

	
	
	@Override
	public List<Animals> listAnimals(Parameter parameter) {
		return animalsMapper.listAnimals(parameter);
	}

	@Override
	public void deleteAnimals(Animals animals) {
		animalsMapper.deleteEntityPhysically(animals);
	}

	@Override
	public void addAnimals(Animals animals) {
		animalsMapper.createEntity(animals);
	}

	@Override
	public void editAnimals(Animals animals) {
		animalsMapper.updateEntity(animals);
	}

	@Override
	public Animals findAnimalsById(Integer id) {
		return animalsMapper.findAnimalsById(id);
	}

	@Override
	public List<AnimalsType> listAllTypes() {
		return typeMapper.listAllTypes();
	}

	@Override
	public List<Animals> listAllAnimals() {
		return animalsMapper.listAllAnimals();
	}
}
