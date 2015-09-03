package com.liuliume.portal.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.OrdersDao;
import com.liuliume.portal.dao.cond.OrdersCond;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.model.OrderStatusEnum;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;

	@Override
	public List<Orders> list(Seed<Orders> seed) throws Exception {

		List<Orders> result = new ArrayList<Orders>();
		Parameter parameter = MBox.convertParameter(seed);

		OrdersCond cond = new OrdersCond();
		Orders orders = new Orders();
		Account account = new Account();
		orders.setAccount(account);

		if (seed.getFilter().containsKey("id")) {
			String id = seed.getFilter().get("id");
			if (StringUtils.isNotBlank(id))
				orders.setOrderId(Integer.parseInt(id));
		}
		if (seed.getFilter().containsKey("account_name")) {
			String account_name = seed.getFilter().get("account_name");
			if (StringUtils.isNotBlank(account_name))
				orders.getAccount().setUniqname(account_name);
		}
		if (seed.getFilter().containsKey("mobile")) {
			String mobile = seed.getFilter().get("mobile");
			if (StringUtils.isNotBlank(mobile))
				orders.getAccount().setMobile(mobile);
		}
		if (seed.getFilter().containsKey("create_time")) {
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
			String create_time = seed.getFilter().get("create_time");
			if (StringUtils.isNotBlank(create_time)) {
				Date date = sf.parse(create_time);
				orders.setCreateTime(date);
			}
		}
		cond.setOrders(orders);
		parameter.setCond(cond);

		int count = ordersDao.count(parameter);
		seed.setTotalSize(count);
		if (count > 0) {
			result = ordersDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

	@Override
	public Orders findOrdersByOrderId(Integer orderId) throws Exception {
		if(orderId==null || orderId<=0)
			return null;
		
		Orders orders=ordersDao.findOrdersByOrderId(orderId);
		return orders;
	}

	@Override
	@Transactional
	public void createOrUpdate(Orders orders) throws Exception {
		if(orders == null)
			throw new IllegalArgumentException("order为空");
		orders.setCreateTime(new Date());
		orders.setStatus(OrderStatusEnum.ORDERED.getId());
		orders.setPaymentStatus(Constants.PAYMENT_NO);
		if(orders.getOrderId()==null || orders.getOrderId()<=0){
			ordersDao.createOrder(orders);
		}else{
			ordersDao.updateOrder(orders);
		}
	}

	
}
