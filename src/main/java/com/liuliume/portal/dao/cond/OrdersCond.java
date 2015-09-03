package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.mybatis.QueryCond;

public class OrdersCond implements QueryCond{

	private Orders orders;

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
}
