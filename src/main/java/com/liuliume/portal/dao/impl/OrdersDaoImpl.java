package com.liuliume.portal.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuliume.portal.dao.OrdersDao;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.OrdersMapper;

@Repository
public class OrdersDaoImpl implements OrdersDao{
	
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public int count(Parameter parameter) {
		return ordersMapper.count(parameter);
	}

	@Override
	public List<Orders> list(Parameter parameter) {
		return ordersMapper.list(parameter);
	}

	@Override
	public Orders findOrdersByOrderId(Integer orderId) throws Exception {
		return ordersMapper.findOrdersByOrderId(orderId);
	}

	@Override
	public void createOrder(Orders orders) {
		ordersMapper.createEntity(orders);
	}

	@Override
	public void updateOrder(Orders orders) {
		ordersMapper.updateEntity(orders);
	}

	@Override
	public int countHairDressingOrders(Date serviceDate, String startTime,
			String endTime) {
		return ordersMapper.countHairDressingOrders(serviceDate, startTime, endTime);
	}

}
