package com.liuliume.portal.service;

import java.util.List;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Orders;

public interface OrdersService {

	public List<Orders> list(Seed<Orders> seed) throws Exception;
	
	public Orders findOrdersByOrderId(Integer orderId)throws Exception;
	
	public void createOrUpdate(Orders orders) throws Exception;
	
	public void payOrder(Integer orderId) throws Exception;
	
	public void invalidOrder(Integer orderId) throws Exception;
	
	public void transferOrder(Integer orderId) throws Exception;
	
	public void completeOrder(Integer orderId) throws Exception;
	
	public void create(Orders orders) throws Exception;
}
