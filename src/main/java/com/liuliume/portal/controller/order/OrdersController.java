package com.liuliume.portal.controller.order;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liuliume.common.util.MD5Util;
import com.liuliume.common.util.ServletUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.model.OrderStatusEnum;
import com.liuliume.portal.model.OrderTypeEnum;
import com.liuliume.portal.service.AccountService;
import com.liuliume.portal.service.AnimalService;
import com.liuliume.portal.service.CourseService;
import com.liuliume.portal.service.HairdressingService;
import com.liuliume.portal.service.OrdersService;
import com.liuliume.portal.service.RoomService;

@Controller
@RequestMapping(value = "orders", method = RequestMethod.GET)
public class OrdersController {

	private Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private HairdressingService hairdressingService;

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

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(
			@RequestParam(value = "ordersId", required = false) String ordersId,
			@RequestParam(value = "orderType", required = true) Integer orderType,
			ModelMap map) {
		logger.info("call OrdersController.index");

		Orders orders = null;
		List<Animals> allAnimals = null;
		List<Room> allRooms = null;
		List<Account> allAccounts = null;
		List<Course> allCourses = null;
		List<Hairdressing> allHairdressings = null;

		try {
			orders = ordersService.findOrdersByOrderId(ordersId);
			allAnimals = animalService.listAllAnimals();
			allRooms = roomService.listAllRooms();
			allAccounts = accountService.listAllAccount();
			allCourses = courseService.listAllCourse();
			allHairdressings = hairdressingService.listAllHairdressings();
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.index! reason:{}, Paramter:ordersId:{},orderType:{}.",
					e.getMessage(), ordersId, orderType, e);
		}

		// Add view models
		if (orders != null) {
			orders.setOrderType(orderType);
			map.put("orders", orders);
		}
		if (allAnimals != null) {
			map.put("allAnimals", allAnimals);
		}
		if (allRooms != null) {
			map.put("allRooms", allRooms);
		}
		if (allAccounts != null) {
			map.put("allAccounts", allAccounts);
		}
		if (allCourses != null) {
			map.put("allCourse", allCourses);
		}
		if (allHairdressings != null) {
			map.put("allHairdressings", allHairdressings);
		}

		map.put("orderType", orderType);

		if (orderType == 1) {// 寄养类型
			return "orders/fosterIndex";
		} else if (orderType == 2) {
			return "orders/trainingIndex";
		} else if (orderType == 3) {
			return "orders/beautyIndex";
		}
		return null;
	}

	@RequestMapping(value = "createOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JData createOrUpdate(Orders orders) {
		logger.info("call the createOrUpdate Orders");
		JData jData = new JData();
		try {
			ordersService.createOrUpdate(orders);
			jData.setCode(200);
			jData.setSuccess(true);
			jData.setDetail("操作成功");
		} catch (Exception e) {
			logger.error("create Or Update  Error." + e.getMessage()
					+ " orders[" + orders + "]", e);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}

	@RequestMapping(value = "orderDetail", method = RequestMethod.GET)
	public String orderDetail(String ordersId, ModelMap map) {

		logger.info("call OrdersController.orderDetail");

		Orders orders = null;

		try {
			orders = ordersService.findOrdersByOrderId(ordersId);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.index! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), ordersId, e);
		}
		if (orders != null) {
			map.put("orders", orders);
		}
		return "orders/orderDetail";
	}

	@RequestMapping(value = "orderDetailForJson", method = RequestMethod.GET)
	@ResponseBody
	public JData orderDetailForJson(String ordersId, ModelMap map) {

		logger.info("call OrdersController.orderDetail");
		JData jData = new JData();

		Orders orders = null;

		try {
			orders = ordersService.findOrdersByOrderId(ordersId);
			jData.setData(orders);
			jData.setSuccess(true);
			jData.setCode(200);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.index! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), ordersId, e);
			jData.setData(null);
			jData.setSuccess(false);
			jData.setCode(500);
		}
		return jData;
	}

	@RequestMapping(value = "payOrder", method = RequestMethod.POST)
	@ResponseBody
	public JData payOrder(
			@RequestParam(value = "orderId", required = true) String orderId) {
		JData jData = new JData("确认收款成功", true);
		try {
			ordersService.payOrder(orderId);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.payOrder! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), orderId, e);
			jData.setDetail(e.getMessage());
			jData.setSuccess(false);
		}
		return jData;
	}

	@RequestMapping(value = "invalidOrder", method = RequestMethod.POST)
	@ResponseBody
	public JData invalidOrder(
			@RequestParam(value = "orderId", required = true) String orderId) {
		JData jData = new JData("订单置无效成功", true);
		try {
			ordersService.invalidOrder(orderId);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.payOrder! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), orderId, e);
			jData.setDetail(e.getMessage());
			jData.setSuccess(false);
		}
		return jData;
	}

	@RequestMapping(value = "transferOrder", method = RequestMethod.POST)
	@ResponseBody
	public JData transferOrder(
			@RequestParam(value = "orderId", required = true) String orderId) {
		JData jData = new JData("订单转发成功", true);
		try {
			ordersService.transferOrder(orderId);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.payOrder! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), orderId, e);
			jData.setDetail(e.getMessage());
			jData.setSuccess(false);
		}
		return jData;
	}

	@RequestMapping(value = "completeOrder", method = RequestMethod.POST)
	@ResponseBody
	public JData completeOrder(
			@RequestParam(value = "orderId", required = true) String orderId) {
		JData jData = new JData("操作成功", true);
		try {
			ordersService.completeOrder(orderId);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.payOrder! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), orderId, e);
			jData.setDetail(e.getMessage());
			jData.setSuccess(false);
		}
		return jData;
	}

	@RequestMapping(value = "refundOrder", method = RequestMethod.POST)
	@ResponseBody
	public JData refundOrder(
			@RequestParam(value = "orderId", required = true) String orderId) {
		JData jData = new JData("操作成功",true);
		try {
			ordersService.refundOrder(orderId);
		} catch (Exception e) {
			logger.error(
					"Error in OrdersController.refundOrder! reason:{}, Paramter:ordersId:{}.",
					e.getMessage(), orderId, e);
			jData.setDetail(e.getMessage());
			jData.setSuccess(false);
		}
		return jData;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public JData create(Orders orders, HttpServletRequest request) {
		logger.info("call the create Orders");
		JData jData = new JData();
		try {
			String mobile = ServletUtil.getCookie(request, "mobile");
			String sgid = ServletUtil.getCookie(request, "sgid");
			if (StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(sgid)
					&& MD5Util.MD5WithSalt(mobile).equals(sgid)) {
				Account account = accountService.findAccountByMobile(mobile);
				orders.setAccount(account);
				orders.setAccountId(account.getAccount_id());
				if (orders.getPaymentType() == null) {
					orders.setPaymentType(2);
				}
				jData.setData(ordersService.create(orders).getOrderId());
				jData.setCode(200);
				jData.setSuccess(true);
				jData.setDetail("操作成功");
			} else {
				jData.setCode(302);
				jData.setSuccess(false);
				jData.setDetail("请登陆！");
			}
		} catch (Exception e) {
			logger.error("create Or Update  Error." + e.getMessage()
					+ " orders[" + orders + "]", e);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail(e.getMessage());
		}
		return jData;
	}

	@RequestMapping(value = "getMoney", method = RequestMethod.POST)
	@ResponseBody
	public JData getMoney(Orders orders, HttpServletRequest request) {
		logger.info("call the create Orders");
		JData jData = new JData();
		try {
			String mobile = ServletUtil.getCookie(request, "mobile");
			String sgid = ServletUtil.getCookie(request, "sgid");
			if (StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(sgid)
					&& MD5Util.MD5WithSalt(mobile).equals(sgid)) {
				Account account = accountService.findAccountByMobile(mobile);
				orders.setAccount(account);
				orders.setAccountId(account.getAccount_id());
				if (orders.getPaymentType() == null) {
					orders.setPaymentType(2);
				}
				jData.setData(ordersService.getMoney(orders).getCost());
				jData.setCode(200);
				jData.setSuccess(true);
				jData.setDetail("操作成功");
			} else {
				jData.setCode(302);
				jData.setSuccess(false);
				jData.setDetail("请登陆！");
			}
		} catch (Exception e) {
			logger.error("create Or Update  Error." + e.getMessage()
					+ " orders[" + orders + "]", e);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail(e.getMessage());
		}
		return jData;
	}

	@RequestMapping(value = "getAllOrders", method = RequestMethod.GET)
	@ResponseBody
	public JData getAllOrders(HttpServletRequest request) {
		JData jData = new JData();
		String mobile = ServletUtil.getCookie(request, "mobile");
		String sgid = ServletUtil.getCookie(request, "sgid");
		String sid = MD5Util.MD5WithSalt(mobile);
		if (StringUtils.isBlank(mobile) || !sid.equals(sgid)) {
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("用户身份验证失败");
		} else {
			Seed<Orders> seed = new Seed<Orders>();
			seed.getFilter().put("mobile", mobile);
			try {
				List<Orders> list = ordersService.list(seed);
				jData.setCode(200);
				jData.setData(list);
				jData.setSuccess(true);
			} catch (Exception e) {
				logger.error("getAllOrders Error." + e.getMessage(), e);
				jData.setCode(500);
				jData.setSuccess(false);
				jData.setDetail("操作失败");
				e.printStackTrace();
			}
		}
		return jData;
	}

	@ModelAttribute("orderTypes")
	public List<OrderTypeEnum> genAllOrderTypeEnums() {
		List<OrderTypeEnum> list = new ArrayList<OrderTypeEnum>();
		for (OrderTypeEnum item : OrderTypeEnum.values()) {
			list.add(item);
		}
		return list;
	}

	@RequestMapping(value = "completeOrderPaymentState", method = RequestMethod.POST)
	@ResponseBody
	public JData completeOrderPaymentState(
			@RequestParam(value = "order_id", required = true) String order_id) {
		logger.info("completeOrderPaymentState更新订单支付状态,order id:{}", order_id);
		JData jData = new JData();
		try {
			ordersService.updateOrderPaymentState(order_id,
					Constants.PAYMENT_YES);
			jData.setDetail("更新订单状态成功");
			jData.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新订单支付状态失败,Order id:{},error_msg:{}", order_id,
					e.getMessage());
			jData.setSuccess(false);
			jData.setDetail("更新订单状态失败");
		}
		return jData;
	}
	
	@RequestMapping(value = "cancelOrderPaymentState", method = RequestMethod.POST)
	@ResponseBody
	public JData cancelOrderPaymentState(@RequestParam(value = "order_id", required = true) String order_id) {
		logger.info("cancelOrderPaymentState更新订单支付状态,order id:{}", order_id);
		JData jData = new JData();
		try {
			ordersService.invalidOrder(order_id);
			jData.setDetail("更新订单状态成功");
			jData.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新订单支付状态失败,Order id:{},error_msg:{}", order_id,
					e.getMessage());
			jData.setSuccess(false);
			jData.setDetail("更新订单状态失败");
		}
		return jData;
	}
}
