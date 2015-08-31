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
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService{

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
		
		if(seed.getFilter().containsKey("typeNameQ")){
			animalsType.setTypeName(seed.getFilter().get("typeNameQ"));
		}
		
		queryCond.setAnimalsType(animalsType);
		parameter.setCond(queryCond);
		
		int count = animalDao.countAnimalsType(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = animalDao.listAnimalsType(parameter);
			seed.setResult(result);
		}
		return result;
	}

	@Override
	@Transactional
	public void batchDeleteAnimalType(String idsStr) throws Exception {
		if(StringUtils.isBlank(idsStr))
			throw new IllegalArgumentException("id为空");
		String[] strs = idsStr.split(",");
		for (String str : strs) {
			AnimalsType type = new AnimalsType();
			type.setId(new Integer(str));
			animalDao.deleteAnimalType(type);
		}
	}

}
