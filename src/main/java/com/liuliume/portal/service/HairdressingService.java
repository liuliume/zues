package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;

import java.util.List;

public interface HairdressingService {
	
	/**
	 * 查询Address表
	 * @param seed
	 * @throws Exception
	 */
	public List<Hairdressing> list(Seed<Hairdressing> seed) throws Exception;

    public Hairdressing findHairdressingById(Integer hairdressing_id) throws Exception;

    public void createOrUpdate(Hairdressing hairdressing) throws Exception;

    public void batchDelete(String hairdressIds) throws Exception;
    
    public List<Hairdressing> listAllHairdressings();
    
}
