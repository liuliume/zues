package com.liuliume.portal.service;

import java.util.List;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Orders;

public interface OrdersService {

	public List<Orders> list(Seed<Orders> seed) throws Exception;
	
	public Orders findOrdersByOrderId(Integer orderId)throws Exception;
}
