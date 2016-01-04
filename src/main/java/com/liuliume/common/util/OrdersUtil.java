package com.liuliume.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.model.OrderTypeEnum;

public class OrdersUtil {
	
	private static final int TYPE_NUM=1;
	private static final int ACCOUNT_NUM=7;
	private static final int TIMESTAMP_NUM=13;
	private static final int RANDOM_NUM=4;

	public static String genOrderNo(Orders orders) {
		if (orders == null) {
			return null;
		}
		OrderTypeEnum orderType = OrderTypeEnum.parse(orders.getOrderType());
		Integer type = 0;
		if (orderType != null) {
			type = orderType.getId();
		}
		// RandomStringUtils.random(count)
		Integer account_id = orders.getAccountId();
		int acc_num = account_id.toString().length();
		String accountPart = RandomStringUtils.randomNumeric(ACCOUNT_NUM-acc_num);
		accountPart+=account_id;
		
		Long timestamp = System.currentTimeMillis();
		int time_num = timestamp.toString().length();
		String timePart = RandomStringUtils.randomNumeric(TIMESTAMP_NUM-time_num);
		timePart+=timestamp;
		
		String randomPart = RandomStringUtils.randomNumeric(RANDOM_NUM);
		
		String order_no = type+accountPart+timePart+randomPart;
		
		orders.setOrderId(order_no);
		return order_no;
	}
	
	public static void main(String[] args) {
		Orders orders = new Orders();
		orders.setOrderType(1);
		orders.setAccountId(1);
		
		String noString = genOrderNo(orders);
		System.out.println(noString);
	}
}
