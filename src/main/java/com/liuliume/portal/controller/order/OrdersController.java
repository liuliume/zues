package com.liuliume.portal.controller.order;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.service.OrdersService;

@Controller
@RequestMapping(value="orders",method=RequestMethod.GET)
public class OrdersController {

	private Logger logger = LoggerFactory.getLogger(OrdersController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(ModelMap map,@SeedParam Seed<Orders> seed){
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
}
