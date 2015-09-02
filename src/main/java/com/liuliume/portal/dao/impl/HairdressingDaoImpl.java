package com.liuliume.portal.dao.impl;

import com.liuliume.portal.dao.CourseDao;
import com.liuliume.portal.dao.HairdressingDao;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.CourseMapper;
import com.liuliume.portal.mybatis.mapper.HairdressingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HairdressingDaoImpl implements HairdressingDao{

	@Autowired
	private HairdressingMapper hairdressingMapper;
	
	@Override
	public int count(Parameter parameter) {
		return hairdressingMapper.count(parameter);
	}

	@Override
	public List<Hairdressing> list(Parameter parameter) {
		return hairdressingMapper.list(parameter);
	}

    @Override
    public Hairdressing findHairdressingById(Integer room_id) {
        return hairdressingMapper.selectByPrimaryKey(room_id);
    }


    @Override
    public void createHairdressing(Hairdressing hairdressing) {
        hairdressingMapper.createEntity(hairdressing);
    }

    @Override
    public void updateHairdressing(Hairdressing hairdressing) {
        hairdressingMapper.updateEntity(hairdressing);
    }

    @Override
    public void delete(Hairdressing hairdressing) {
        hairdressingMapper.deleteEntityPhysically(hairdressing);
    }


}
