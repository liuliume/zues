package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;
import com.liuliume.portal.mybatis.QueryCond;

public class HairdressingTimeQueryCond implements QueryCond{
	
	private HairdressingTime hairdressingTime;

	public HairdressingTime getHairdressingTime() {
		return hairdressingTime;
	}

	public void setHairdressingTime(HairdressingTime hairdressingTime) {
		this.hairdressingTime = hairdressingTime;
	}
	
}
