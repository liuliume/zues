package com.liuliume.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AnimalDao;
import com.liuliume.portal.dao.cond.AnimalTypeQueryCond;
import com.liuliume.portal.dao.cond.AnimalsCond;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AnimalDao animalDao;

	@Override
	public List<AnimalsType> list(Seed<AnimalsType> seed) throws Exception {
		logger.info("Account Service list bengins.");
		List<AnimalsType> result = new ArrayList<AnimalsType>();

		Parameter parameter = MBox.convertParameter(seed);

		AnimalTypeQueryCond queryCond = new AnimalTypeQueryCond();
		AnimalsType animalsType = new AnimalsType();

		if (seed.getFilter().containsKey("typeNameQ")) {
			animalsType.setTypeName(seed.getFilter().get("typeNameQ"));
		}

		queryCond.setAnimalsType(animalsType);
		parameter.setCond(queryCond);

		int count = animalDao.countAnimalsType(parameter);
		seed.setTotalSize(count);
		if (count > 0) {
			result = animalDao.listAnimalsType(parameter);
			seed.setResult(result);
		}
		return result;
	}

	@Override
	@Transactional
	public void batchDeleteAnimalType(String idsStr) throws Exception {
		if (StringUtils.isBlank(idsStr))
			throw new IllegalArgumentException("id为空");
		String[] strs = idsStr.split(",");
		for (String str : strs) {
			AnimalsType type = new AnimalsType();
			type.setId(new Integer(str));
			animalDao.deleteAnimalType(type);
		}
	}

	@Override
	public void createOrUpdateType(AnimalsType type) throws Exception {
		if (type == null)
			throw new IllegalArgumentException("参数错误，宠物类新为空");
		if (type.getId() == null) {
			animalDao.insertAnimalType(type);
		} else {
			animalDao.updateAnimalType(type);
		}
	}

	@Override
	public AnimalsType findAnimalsTypeById(Integer id) {
		if (id != null)
			return animalDao.findAnimalsTypeById(id);
		return null;
	}

	@Override
	public List<Animals> listAnimals(Seed<Animals> seed) throws Exception {
		// TODO Auto-generated method stub
		logger.info("AnimalService.listAnimals begins");

		List<Animals> result = new ArrayList<Animals>();
		Parameter parameter = MBox.convertParameter(seed);

		AnimalsCond cond = new AnimalsCond();

		Animals animals = new Animals();

		if (seed.getFilter().containsKey("nameQ")) {
			String name = seed.getFilter().get("nameQ");
			animals.setAnimalsName(name);
		}

		cond.setAnimals(animals);
		parameter.setCond(cond);

		int count = animalDao.countAnimals(parameter);
		seed.setTotalSize(count);
		if (count > 0) {
			result = animalDao.listAnimals(parameter);
			seed.setResult(result);
		}
		return result;
	}

	@Override
	@Transactional
	public void batchDeleteAnimals(String idsStr) throws Exception {
		if (StringUtils.isBlank(idsStr))
			throw new IllegalArgumentException("id为空");
		String[] strs = idsStr.split(",");
		for (String id : strs) {
			Animals animals = new Animals();
			animals.setId(new Integer(id));
			animalDao.deleteAnimals(animals);
		}
	}

	@Override
	public void createOrUpdateAnimals(Animals animals) throws Exception {
		if (animals == null)
			throw new IllegalArgumentException("animals为空");
		if (animals.getId() == null) {
			animalDao.addAnimals(animals);
		} else {
			animalDao.editAnimals(animals);
		}
	}

	@Override
	public Animals findAnimalsById(Integer id) {
		if (id != null)
			return animalDao.findAnimalsById(id);
		return null;
	}

	@Override
	public List<AnimalsType> listAllTypes() {
		return animalDao.listAllTypes();
	}

	@Override
	public List<Animals> listAllAnimals() {
		return animalDao.listAllAnimals();
	}

}
