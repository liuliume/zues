package com.liuliume.portal.controller.count;

import com.liuliume.portal.common.JData;
import com.liuliume.portal.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clement on 9/6/15.
 */
@Controller
@RequestMapping(value = { "/count" }, method = RequestMethod.GET)
public class CountController {

	private Logger logger = LoggerFactory.getLogger(CountController.class);

	@Autowired
	private CountService countService;

	@RequestMapping(value = "room_count", method = RequestMethod.GET)
	@ResponseBody
	public JData roomCountMoney(
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate,
			@RequestParam(value = "room_id", required = true) Integer room_id,
			@RequestParam(value = "animals_id", required = true) Integer animals_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		JData jData = new JData();
		try {
			Date start_Date = sdf.parse(startDate);
			Date end_Date = sdf.parse(endDate);
			double money = countService.roomCountMoney(start_Date, end_Date,
					room_id, animals_id);
			jData.setData(money);
			jData.setCode(200);
			jData.setSuccess(true);
			jData.setDetail("获取费用成功");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get Account list error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), null), e);
			jData.setData(null);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("获取费用失败");
		}
		return jData;
	}

	@RequestMapping(value = "course_count", method = RequestMethod.GET)
	@ResponseBody
	public JData courseCountMoney(
			@RequestParam(value = "course_id", required = true) Integer course_id,
			@RequestParam(value = "animals_id", required = true) Integer animals_id) {
		double money = 0.0;
		JData jData = new JData();
		try {
			money = countService.courseCountMoney(course_id, animals_id);
			jData.setData(money);
			jData.setCode(200);
			jData.setSuccess(true);
			jData.setDetail("获取费用成功");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get Account list error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), null), e);
			jData.setData(null);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("获取费用失败");
		}
		return jData;
	}

	@RequestMapping(value = "hairDressing_count", method = RequestMethod.GET)
	@ResponseBody
	public JData hairDressingCountMoney(
			@RequestParam(value = "animals_id", required = true) Integer animals_id,
			@RequestParam(value = "hairDressing_id", required = true) Integer hairDressing_id) {
		double money = 0.0;
		JData jData = new JData();
		try {
			money = countService.hairDressingCountMoney(animals_id, hairDressing_id);
			jData.setData(money);
			jData.setCode(200);
			jData.setSuccess(true);
			jData.setDetail("获取费用成功");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get hairDressingCountMoney error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), null), e);
			jData.setData(null);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("获取费用失败");
		}
		return jData;
	}
}
