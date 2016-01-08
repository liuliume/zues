package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AnimalDao;
import com.liuliume.portal.dao.CourseDao;
import com.liuliume.portal.dao.HairdressingDao;
import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.dao.cond.RoomQueryCond;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.AnimalsType;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
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

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private HairdressingDao hairdressingDao;

	@Override
	public double roomCountMoney(Date startDate, Date endDate, Integer room_id,
			Integer animals_id, boolean isWechatPayment) {
		Room room = null;
		Animals animals = null;
		AnimalsType animalsType = null;
		if (StringUtils.isNotBlank(String.valueOf(room_id))) {
			room = roomDao.findRoomById(room_id);
		}
		if (StringUtils.isNotBlank(String.valueOf(animals_id))) {
			animals = animalDao.findAnimalsById(animals_id);
		}
		int type_id = animals.getTypeId();
		animalsType = animalDao.findAnimalsTypeById(type_id);
		int days = daysBetween(startDate, endDate);
		double money = 0.0;
		if (room != null && animalsType != null) {
			if (days >= 30 && days < 90) {
				money = room.getCost() * room.getDiscount30()
						* animalsType.getExpenseCoefficient() * days;
			} else if (days >= 90 && days < 180) {
				money = room.getCost() * room.getDiscount90()
						* animalsType.getExpenseCoefficient() * days;
			} else if (days >= 180) {
				money = room.getCost() * room.getDiscount180()
						* animalsType.getExpenseCoefficient() * days;
			} else {
				money = room.getCost() * animalsType.getExpenseCoefficient()
						* days;
			}
			if (isWechatPayment && room.getWeixinDiscount() != null) {
				money = money * room.getWeixinDiscount();
			}
		}
		return money;
	}
	
	@Override
	public double roomCountFullAmountMoney(Date startDate, Date endDate, Integer room_id,
			Integer animals_id){
		Room room = null;
		Animals animals = null;
		AnimalsType animalsType = null;
		if (StringUtils.isNotBlank(String.valueOf(room_id))) {
			room = roomDao.findRoomById(room_id);
		}
		if (StringUtils.isNotBlank(String.valueOf(animals_id))) {
			animals = animalDao.findAnimalsById(animals_id);
		}
		int type_id = animals.getTypeId();
		animalsType = animalDao.findAnimalsTypeById(type_id);
		int days = daysBetween(startDate, endDate);
		double money = 0.0;
		if (room != null && animalsType != null) {
			money = room.getCost() * days;
		}
		return money;
	}

	@Override
	public double courseCountMoney(Integer course_id, Integer animals_id) {
		Course course = null;
		Animals animals = null;
		AnimalsType animalsType = null;
		double money = 0.0;
		if (StringUtils.isNotBlank(String.valueOf(course_id))) {
			course = courseDao.findCourseById(course_id);
		}
		if (StringUtils.isNotBlank(String.valueOf(animals_id))) {
			animals = animalDao.findAnimalsById(animals_id);
		}
		int type_id = animals.getTypeId();
		animalsType = animalDao.findAnimalsTypeById(type_id);
		if (course != null && animalsType != null) {
			money = course.getExpense() * animalsType.getExpenseCoefficient();
		}
		return money;
	}

	public static int daysBetween(Date startDate, Date endDate) {
		Calendar s = Calendar.getInstance();
		Calendar e = Calendar.getInstance();
		s.setTime(startDate);
		e.setTime(endDate);
		long days = (e.getTimeInMillis() - s.getTimeInMillis())
				/ (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(days));
	}

	@Override
	public double hairDressingCountMoney(Integer animals_id,
			Integer hairDressing_id) throws Exception {
		double money = 0;

		if (animals_id == null || animals_id <= 0 || hairDressing_id == null
				|| hairDressing_id <= 0) {
			throw new IllegalArgumentException("参数错误");
		}
		Animals animals = animalDao.findAnimalsById(animals_id);
		if (animals == null) {
			throw new Exception("该宠物不存在");
		}
		int type_id = animals.getTypeId();
		AnimalsType animalsType = animalDao.findAnimalsTypeById(type_id);

		Hairdressing hairdress = hairdressingDao
				.findHairdressingById(hairDressing_id);
		if (hairdress == null) {
			throw new Exception("服务项目不存在");
		}
		money = hairdress.getExpense() * animalsType.getExpenseCoefficient();
		return money;
	}

}
