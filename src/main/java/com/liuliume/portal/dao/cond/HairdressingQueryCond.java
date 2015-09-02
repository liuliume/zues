package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.mybatis.QueryCond;

public class HairdressingQueryCond implements QueryCond{
	
	private Hairdressing hairdressing;

	public Hairdressing getHairdressing() {
		return hairdressing;
	}

	public void setHairdressing(Hairdressing hairdressing) {
		this.hairdressing = hairdressing;
	}
	
}
