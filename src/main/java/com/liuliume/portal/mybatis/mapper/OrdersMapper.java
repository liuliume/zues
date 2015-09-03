package com.liuliume.portal.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.mybatis.Parameter;

public interface OrdersMapper {
	
	public int count(@Param("param")Parameter parameter);
	
	public List<Orders> list(@Param("param")Parameter parameter);
	
	public Orders findOrdersByOrderId(@Param("orderId")Integer orderId);
}