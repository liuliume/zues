package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.mybatis.QueryCond;

public class AnimalsCond implements QueryCond{

	private Animals animals;

	public Animals getAnimals() {
		return animals;
	}

	public void setAnimals(Animals animals) {
		this.animals = animals;
	}
}
