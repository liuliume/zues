package com.liuliume.portal.dao.impl;

import com.liuliume.portal.dao.HairdressingDao;
import com.liuliume.portal.dao.HairdressingTimeDao;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.HairdressingMapper;
import com.liuliume.portal.mybatis.mapper.HairdressingTimeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HairdressingTimeDaoImpl implements HairdressingTimeDao{

	@Autowired
	private HairdressingTimeMapper hairdressingTimeMapper;
	
	@Override
	public int count(Parameter parameter) {
		return hairdressingTimeMapper.count(parameter);
	}

	@Override
	public List<HairdressingTime> list(Parameter parameter) {
		return hairdressingTimeMapper.list(parameter);
	}

    @Override
    public HairdressingTime findHairdressingTimeById(Integer room_id) {
        return hairdressingTimeMapper.selectByPrimaryKey(room_id);
    }


    @Override
    public void createHairdressingTime(HairdressingTime hairdressingTime) {
        hairdressingTimeMapper.createEntity(hairdressingTime);
    }

    @Override
    public void updateHairdressingTime(HairdressingTime hairdressingTime) {
        hairdressingTimeMapper.updateEntity(hairdressingTime);
    }

    @Override
    public void delete(HairdressingTime hairdressingTime) {
        hairdressingTimeMapper.deleteEntityPhysically(hairdressingTime);
    }

	@Override
	public List<HairdressingTime> listAllHairdressingTime() {
		return hairdressingTimeMapper.listAllHairdressingTime();
	}

	@Override
	public HairdressingTime getHairdressingTimeByStartTime(String startTime) {
		return hairdressingTimeMapper.getHairdressingTimeByStartTime(startTime);
	}


}
