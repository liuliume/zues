package com.liuliume.portal.dao;

import java.util.Date;
import java.util.List;

import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.mybatis.Parameter;

public interface OrdersDao {

	public int count(Parameter parameter);
	
	public List<Orders> list(Parameter parameter);
	
	public Orders findOrdersByOrderId(String orderId) throws Exception;
	
	public void createOrder(Orders orders);
	
	public void updateOrder(Orders orders);
	
	/**
	 * 获取某个时间段的美容订单数量
	 * @param serviceDate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int countHairDressingOrders(Date serviceDate,String startTime,String endTime);

    public int countRoomOrders(String startTime,String endTime);
}
