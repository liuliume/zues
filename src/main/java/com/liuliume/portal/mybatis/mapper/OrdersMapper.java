package com.liuliume.portal.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;

public interface OrdersMapper extends MyBatisBaseMapper<Orders> {

	public int count(@Param("param") Parameter parameter);

	public List<Orders> list(@Param("param") Parameter parameter);

	public Orders findOrdersByOrderId(@Param("orderId") String orderId);

	public int countHairDressingOrders(@Param("serviceDate") Date serviceDate,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime);

    public int countRoomOrders(@Param("startDate") String startDate);

    public int countRoomOrdersForEndDate(@Param("endDate") String endDate);
}