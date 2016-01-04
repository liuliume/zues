package com.liuliume.portal.service;

import java.util.List;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Orders;

public interface OrdersService {

	public List<Orders> list(Seed<Orders> seed) throws Exception;
	
	public Orders findOrdersByOrderId(String orderId)throws Exception;
	
	public void createOrUpdate(Orders orders) throws Exception;
	
	public void payOrder(String orderId) throws Exception;
	
	public void invalidOrder(String orderId) throws Exception;
	
	public void transferOrder(String orderId) throws Exception;
	
	public void completeOrder(String orderId) throws Exception;
	
	public void refundOrder(String orderId) throws Exception;
	
	public Orders create(Orders orders) throws Exception;

    public Orders getMoney(Orders orders) throws Exception;
    
    public void updateOrderPaymentState(String orders_id,int state) throws Exception;
}
