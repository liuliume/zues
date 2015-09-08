package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AnimalDao;
import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.dao.cond.RoomQueryCond;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.CountService;
import com.liuliume.portal.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CountServiceImpl implements CountService {

	private Logger logger = LoggerFactory.getLogger(CountServiceImpl.class);

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private AnimalDao animalDao;

    public double roomCountMoney(Date startDate,Date endDate,Integer room_id,Integer animals_id){
        Room room = null;
        Animals animals = null;
        AnimalsType animalsType = null;
        if(StringUtils.isNotBlank(String.valueOf(room_id))) {
            room = roomDao.findRoomById(room_id);
        }
        if(StringUtils.isNotBlank(String.valueOf(animals_id))) {
            animals = animalDao.findAnimalsById(animals_id);
        }
        int type_id = animals.getTypeId();
        animalsType = animalDao.findAnimalsTypeById(type_id);
        int days = daysBetween(startDate,endDate);
        double money = 0.0;
        if(room!=null && animalsType!=null) {
            if(days >= 30 && days < 90) {
                money = room.getCost() * room.getDiscount30() * animalsType.getExpenseCoefficient() * days;
            } else if(days >= 90 && days < 180) {
                money = room.getCost() * room.getDiscount90() * animalsType.getExpenseCoefficient() * days;
            } else if(days >= 180) {
                money = room.getCost() * room.getDiscount180() * animalsType.getExpenseCoefficient() * days;
            } else {
                money = room.getCost() * animalsType.getExpenseCoefficient() * days;
            }
        }
        return money;
    }


    public static int daysBetween(Date startDate,Date endDate) {
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        s.setTime(startDate);
        e.setTime(endDate);
        long days = (e.getTimeInMillis() - s.getTimeInMillis())/(1000*3600*24);
        return Integer.parseInt(String.valueOf(days));
    }


}