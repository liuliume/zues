package com.liuliume.portal.controller.order;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.model.OrderStatusEnum;
import com.liuliume.portal.model.OrderTypeEnum;
import com.liuliume.portal.service.OrdersService;

@Controller
@RequestMapping(value = "orders", method = RequestMethod.GET)
public class OrdersController {

	private Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrdersService ordersService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap map, @SeedParam Seed<Orders> seed) {
		logger.info("list orders begin.");
		try {
			ordersService.list(seed);
			seed.setActionPath("orders/list");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get orders list error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), seed.toString()), e);
		}
		map.put("seed", seed);
		return "orders/list";
	}

	@RequestMapping(value="index",method = RequestMethod.GET)
	public String index(
			@RequestParam(value = "ordersId", required = false) Integer ordersId,
			@RequestParam(value = "orderType", required = true) Integer orderType,
			ModelMap map) {
		logger.info("call OrdersController.index");
		
		Orders orders = null;
		
		try {
			orders = ordersService.findOrdersByOrderId(ordersId);
		} catch (Exception e) {
			logger.error("Error in OrdersController.index! reason:{}, Paramter:ordersId:{},orderType:{}.",
					e.getMessage(),ordersId,orderType,e);
		}
		
		if(orders!=null)
			map.put("orders", orders);
		
		if(orderType==1){//寄养类型
			return "orders/fosterIndex";
		}else if(orderType==2){
			return "orders/trainingIndex";
		}else if(orderType==3){
			return "orders/beautyIndex";
		}
		return null;
	}

	@ModelAttribute("orderTypes")
	public List<OrderTypeEnum> genAllOrderTypeEnums() {
		List<OrderTypeEnum> list = new ArrayList<OrderTypeEnum>();
		for (OrderTypeEnum item : OrderTypeEnum.values()) {
			list.add(item);
		}
		return list;
	}
}
