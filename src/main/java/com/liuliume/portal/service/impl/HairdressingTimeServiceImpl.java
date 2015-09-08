package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.HairdressingDao;
import com.liuliume.portal.dao.HairdressingTimeDao;
import com.liuliume.portal.dao.OrdersDao;
import com.liuliume.portal.dao.cond.HairdressingQueryCond;
import com.liuliume.portal.dao.cond.HairdressingTimeQueryCond;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.HairdressingTime;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.HairdressingService;
import com.liuliume.portal.service.HairdressingTimeService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HairdressingTimeServiceImpl implements HairdressingTimeService {

	private Logger logger = LoggerFactory.getLogger(HairdressingTimeServiceImpl.class);
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private HairdressingTimeDao hairdressingTimeDao;

	@Override
	public List<HairdressingTime> list(Seed<HairdressingTime> seed) throws Exception{
		logger.info("Address Service list bengins.");
		List<HairdressingTime> result = new ArrayList<HairdressingTime>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		HairdressingTimeQueryCond cond = new HairdressingTimeQueryCond();
        HairdressingTime hairdressingTime = new HairdressingTime();

        if(seed.getFilter().containsKey("startTime")){
            String name = seed.getFilter().get("startTime");
            if(!StringUtils.isEmpty(name))
                hairdressingTime.setStartTime(name);
        }
        if(seed.getFilter().containsKey("endTime")){
            String name = seed.getFilter().get("endTime");
            if(!StringUtils.isEmpty(name))
                hairdressingTime.setEndTime(name);
        }
		//add other query condition here
		
		cond.setHairdressingTime(hairdressingTime);
		parameter.setCond(cond);
		
		int count = hairdressingTimeDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = hairdressingTimeDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

    @Override
    public HairdressingTime findHairdressingTimeById(Integer hairdressingTime_id) throws Exception {
        HairdressingTime hairdressingTime = null;
        if(hairdressingTime_id!=null)
            hairdressingTime = hairdressingTimeDao.findHairdressingTimeById(hairdressingTime_id);
        return hairdressingTime;
    }

    @Override
    public void createOrUpdate(HairdressingTime hairdressingTime) throws Exception {
        if(hairdressingTime==null)
            throw new IllegalArgumentException("hairdressing为空");
        if(null == hairdressingTime.getId()){//add
            hairdressingTimeDao.createHairdressingTime(hairdressingTime);
        }else {
            hairdressingTimeDao.updateHairdressingTime(hairdressingTime);
        }
    }

    @Override
    @Transactional
    public void batchDelete(String hairdressingTimeIds) throws Exception {
        String[] aids = hairdressingTimeIds.split(",");
        for (String aid : aids) {
            HairdressingTime hairdressingTime= new HairdressingTime();
            hairdressingTime.setId(new Integer(aid));
            hairdressingTimeDao.delete(hairdressingTime);
        }
    }

	@Override
	public List<HairdressingTime> listValidHairingDressingTime(Date serviceDate)
			throws Exception {
		List<HairdressingTime> list = hairdressingTimeDao.listAllHairdressingTime();
		if(CollectionUtils.isNotEmpty(list)){
			for (HairdressingTime item : list) {
				String startTime = item.getStartTime();
				String endTime = item.getEndTime();
				int count = ordersDao.countHairDressingOrders(serviceDate, startTime, endTime);
				if(item.getServicePersionNum().compareTo(new Integer(count))<=0){
					list.remove(item);
					continue;
				}
				item.setServicePersionNum(item.getServicePersionNum()-count);
			}
			return list;
		}
		return null;
	}

}
