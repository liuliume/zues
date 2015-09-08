package com.liuliume.portal.service;


import java.util.Date;

public interface CountService {

    public double roomCountMoney(Date startDate,Date endDate,Integer room_id,Integer animals_id);

    public double courseCountMoney(Integer course_id,Integer animals_id);
}
