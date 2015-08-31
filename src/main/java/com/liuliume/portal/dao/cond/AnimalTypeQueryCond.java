package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.mybatis.QueryCond;

public class AnimalTypeQueryCond implements QueryCond{

	private AnimalsType animalsType;

	public AnimalsType getAnimalsType() {
		return animalsType;
	}

	public void setAnimalsType(AnimalsType animalsType) {
		this.animalsType = animalsType;
	}
	
}
