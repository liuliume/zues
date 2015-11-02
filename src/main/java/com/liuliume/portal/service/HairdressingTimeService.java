package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;

import java.util.Date;
import java.util.List;

public interface HairdressingTimeService {
	
	/**
	 * 查询Address表
	 * @param seed
	 * @throws Exception
	 */
	public List<HairdressingTime> list(Seed<HairdressingTime> seed) throws Exception;

    public HairdressingTime findHairdressingTimeById(Integer hairdressingTime_id) throws Exception;

    public void createOrUpdate(HairdressingTime hairdressingTime) throws Exception;

    public void batchDelete(String hairdressingIds) throws Exception;
    
    public List<HairdressingTime> listValidHairingDressingTime(Date serviceDate) throws Exception;
    
    public boolean isServiceTimeValid(String serviceTime,int service_type) throws Exception;
}
