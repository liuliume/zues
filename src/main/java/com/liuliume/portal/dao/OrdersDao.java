package com.liuliume.portal.dao;

import java.util.List;

import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.mybatis.Parameter;

public interface OrdersDao {

	public int count(Parameter parameter);
	
	public List<Orders> list(Parameter parameter);
}
