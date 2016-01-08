package com.liuliume.portal.service;

import java.util.Date;

public interface CountService {

	public double roomCountMoney(Date startDate, Date endDate, Integer room_id,
			Integer animals_id, boolean isWechatPayment);

	public double courseCountMoney(Integer course_id, Integer animals_id);

	public double hairDressingCountMoney(Integer animals_id,
			Integer hairDressing_id) throws Exception;
	
	public double roomCountFullAmountMoney(Date startDate, Date endDate, Integer room_id,
			Integer animals_id);
}
