package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.CourseDao;
import com.liuliume.portal.dao.HairdressingDao;
import com.liuliume.portal.dao.cond.CourseQueryCond;
import com.liuliume.portal.dao.cond.HairdressingQueryCond;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.CourseService;
import com.liuliume.portal.service.HairdressingService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HairdressingServiceImpl implements HairdressingService {

	private Logger logger = LoggerFactory.getLogger(HairdressingServiceImpl.class);
	
	@Autowired
	private HairdressingDao hairdressingDao;

	@Override
	public List<Hairdressing> list(Seed<Hairdressing> seed) throws Exception{
		logger.info("Address Service list bengins.");
		List<Hairdressing> result = new ArrayList<Hairdressing>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		HairdressingQueryCond cond = new HairdressingQueryCond();
        Hairdressing hairdressing = new Hairdressing();

		if(seed.getFilter().containsKey("name")){
			String name = seed.getFilter().get("name");
            if(!StringUtils.isEmpty(name))
                hairdressing.setHairdressingName(name);
		}
		//add other query condition here
		
		cond.setHairdressing(hairdressing);
		parameter.setCond(cond);
		
		int count = hairdressingDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = hairdressingDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

    @Override
    public Hairdressing findHairdressingById(Integer hairdressing_id) throws Exception {
        Hairdressing hairdressing = null;
        if(hairdressing_id!=null)
            hairdressing = hairdressingDao.findHairdressingById(hairdressing_id);
        return hairdressing;
    }

    @Override
    public void createOrUpdate(Hairdressing hairdressing) throws Exception {
        if(hairdressing==null)
            throw new IllegalArgumentException("hairdressing为空");
        if(null == hairdressing.getId()){//add
            hairdressingDao.createHairdressing(hairdressing);
        }else {
            hairdressingDao.updateHairdressing(hairdressing);
        }
    }

    @Override
    @Transactional
    public void batchDelete(String hairdressingIds) throws Exception {
        String[] aids = hairdressingIds.split(",");
        for (String aid : aids) {
            Hairdressing hairdressing= new Hairdressing();
            hairdressing.setId(new Integer(aid));
            hairdressingDao.delete(hairdressing);
        }
    }

	@Override
	public List<Hairdressing> listAllHairdressings() {
		return hairdressingDao.listAllHairdressings();
	}

}
