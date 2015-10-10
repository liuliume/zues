package com.liuliume.portal.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.util.OrdersUtil;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.OrdersDao;
import com.liuliume.portal.dao.cond.OrdersCond;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.entity.Animals;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.entity.Orders;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.model.OrderStatusEnum;
import com.liuliume.portal.model.OrderTypeEnum;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.AddressService;
import com.liuliume.portal.service.AnimalService;
import com.liuliume.portal.service.CountService;
import com.liuliume.portal.service.CourseService;
import com.liuliume.portal.service.HairdressingService;
import com.liuliume.portal.service.OrdersService;
import com.liuliume.portal.service.RoomService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private RoomService roomService;
	@Autowired
	private AnimalService animalService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CountService countService;
	@Autowired
	private HairdressingService hairdressingService;
	@Autowired
	private AddressService addressService;

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
				orders.setOrderId(id);
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
	public Orders findOrdersByOrderId(String orderId) throws Exception {
		if (StringUtils.isEmpty(orderId))
			return null;

		Orders orders = ordersDao.findOrdersByOrderId(orderId);
		return orders;
	}

	@Override
	@Transactional
	public void createOrUpdate(Orders orders) throws Exception {
//		if (orders == null)
//			throw new IllegalArgumentException("order为空");
//		orders.setCreateTime(new Date());
//		orders.setStatus(OrderStatusEnum.ORDERED.getId());
//		orders.setPaymentStatus(Constants.PAYMENT_NO);
//		if (orders.getOrderId() == null || orders.getOrderId() <= 0) {
//			ordersDao.createOrder(orders);
//		} else {
//			ordersDao.updateOrder(orders);
//		}
	}

	@Override
	@Transactional
	public void payOrder(String orderId) throws Exception {
		if (StringUtils.isEmpty(orderId)) {
			throw new IllegalArgumentException("订单ID错误,请检查");
		}
		Orders orders = findOrdersByOrderId(orderId);
		if (orders.getPaymentStatus() == Constants.PAYMENT_YES) {
			throw new Exception("订单已支付,请检查");
		}
		orders.setPaymentStatus(Constants.PAYMENT_YES);
		orders.setPaymentType(Constants.PAYMENTTYPE_OFFLINE);
		ordersDao.updateOrder(orders);
	}

	@Override
	@Transactional
	public void invalidOrder(String orderId) throws Exception {
		if (StringUtils.isEmpty(orderId)) {
			throw new IllegalArgumentException("订单ID错误,请检查");
		}
		Orders orders = findOrdersByOrderId(orderId);
		if (orders.getStatus() == OrderStatusEnum.INVALID.getId()) {
			throw new Exception("订单不能重复置无效");
		}
		if (orders.getStatus() >= OrderStatusEnum.SERVICING.getId()) {
			throw new Exception("订单已经开始服务,不能置无效");
		}
		if (orders.getPaymentStatus() == Constants.PAYMENT_YES) {
			throw new Exception("订单已经付款,不能置无效,请先退款");
		}
		orders.setStatus(OrderStatusEnum.INVALID.getId());
		ordersDao.updateOrder(orders);
	}

	@Override
	@Transactional
	public void transferOrder(String orderId) throws Exception {
		if (orderId == null)
			throw new IllegalArgumentException("Order Id错误");
		Orders orders = findOrdersByOrderId(orderId);
		if (orders == null)
			throw new Exception("订单ID[" + orderId + "]对应的订单不存在");
		if (orders.getOrderType() != OrderTypeEnum.BEAUTY.getId()) {
			throw new Exception("订单ID[" + orderId + "]不是美容订单,不能转发");
		}
		if (orders.getStatus() != OrderStatusEnum.ORDERED.getId()) {
			throw new Exception("订单ID[" + orderId + "]不是下单状态,不能转发");
		}
		if (orders.getServiceType() != Constants.SERVICE_DOOR) {
			throw new Exception("订单ID[" + orderId + "]不是上门服务,不能转发");
		}
		orders.setStatus(OrderStatusEnum.TRANSFER.getId());
		ordersDao.updateOrder(orders);
	}

	@Override
	public void completeOrder(String orderId) throws Exception {
		if (orderId == null)
			throw new IllegalArgumentException("Order Id错误");
		Orders orders = findOrdersByOrderId(orderId);
		if (orders == null)
			throw new Exception("订单ID[" + orderId + "]对应的订单不存在");
		if (orders.getStatus() == OrderStatusEnum.COMPLETE.getId()) {
			throw new Exception("订单ID[" + orderId + "]已经完成,不能重复操作");
		}
		if (orders.getStatus() == OrderStatusEnum.INVALID.getId()
				|| orders.getStatus() == OrderStatusEnum.DELETE.getId()) {
			throw new Exception("订单ID[" + orderId + "]无效,不能完成");
		}
		orders.setStatus(OrderStatusEnum.COMPLETE.getId());
		ordersDao.updateOrder(orders);
	}

	@Override
	@Transactional
	public Orders create(Orders orders) throws Exception {
		if (orders == null)
			throw new IllegalArgumentException("订单不能为空");
		if (orders.getOrderType() == null)
			throw new IllegalArgumentException("订单类型不能为空");
		OrdersUtil.genOrderNo(orders);
		OrderTypeEnum orderTypeEnum = OrderTypeEnum
				.parse(orders.getOrderType());
        orders.setStatus(OrderStatusEnum.ORDERED.getId());
        switch (orderTypeEnum) {
		case FOSTER:// 寄养订单
			_createFosterOrder(orders);
			break;
		case TRAINING:// 训练订单
			_createTrainingOrders(orders);
			break;
		case BEAUTY:// 美容订单
			_createBeautyOrders(orders);
			break;
		default:
			throw new IllegalArgumentException("订单类型错误");
		}
        return orders;
	}

	private void _createFosterOrder(Orders orders) throws Exception {
		if (StringUtils.isBlank(orders.getStartDate())) {
			throw new Exception("开始时间不能为空");
		}
		if (StringUtils.isBlank(orders.getEndDate())) {
			throw new Exception("结束时间不能为空");
		}
		if (orders.getRoomId() == null || orders.getRoomId() <= 0) {
			throw new IllegalArgumentException("寄养房间必填");
		}
		Room room = roomService.findRoomById(orders.getRoomId());
		if (room == null) {
			throw new Exception("寄养房间类型错误");
		}
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(orders.getStartDate());
		Date endDate = sdf.parse(orders.getEndDate());
		boolean empty = roomService.isRoomNotEmpty(startDate, endDate,
				orders.getRoomId());
		if (!empty) {
			throw new Exception("该房间已满,请选择其他房间类型");
		}
		if (orders.getAnimalsId() == null || orders.getAnimalsId() <= 0) {
			throw new Exception("宠物类型不能为空");
		}
		Animals animals = animalService.findAnimalsById(orders.getAnimalsId());
		if (animals == null) {
			throw new Exception("宠物类型错误");
		}
		double cost = countService.roomCountMoney(startDate, endDate,
				orders.getRoomId(), orders.getAnimalsId());
		orders.setCost(cost);
		orders.setCreateTime(new Date());
		ordersDao.createOrder(orders);
	}

	private void _createTrainingOrders(Orders orders) throws Exception {
		if (orders.getCourseId() == null || orders.getCourseId() <= 0) {
			throw new IllegalArgumentException("训练课程不能为空");
		}
		Course course = courseService.findCourseById(orders.getCourseId());
		if (course == null) {
			throw new Exception("课程类型错误");
		}
		if (orders.getAnimalsId() == null || orders.getAnimalsId() <= 0) {
			throw new Exception("宠物类型不能为空");
		}
		Animals animals = animalService.findAnimalsById(orders.getAnimalsId());
		if (animals == null) {
			throw new Exception("宠物类型错误");
		}
		double cost = countService.courseCountMoney(orders.getCourseId(),
				orders.getAnimalsId());
		orders.setCost(cost);
		orders.setCreateTime(new Date());
		ordersDao.createOrder(orders);
	}

	private void _createBeautyOrders(Orders orders) throws Exception {
		if (orders.getHairdressId() == null || orders.getHairdressId() <= 0) {
			throw new IllegalArgumentException("美容服务项目不能为空");
		}
		Hairdressing hairdressing = hairdressingService
				.findHairdressingById(orders.getHairdressId());
		if (hairdressing == null) {
			throw new Exception("美容服务项目不存在");
		}
		if (orders.getAnimalsId() == null || orders.getAnimalsId() <= 0) {
			throw new Exception("宠物类型不能为空");
		}
		Animals animals = animalService.findAnimalsById(orders.getAnimalsId());
		if (animals == null) {
			throw new Exception("宠物类型错误");
		}
		if (orders.getServiceType() == null || orders.getServiceType() < 0) {
			throw new IllegalArgumentException("服务方式不能为空");
		}
		if (orders.getServiceType() != Constants.SERVICE_DOOR
				&& orders.getServiceType() != Constants.SERVICE_SHOP) {
			throw new IllegalArgumentException("服务方式错误,只能为上门服务和到店服务");
		}
		if (orders.getServiceType() != Constants.SERVICE_DOOR) {// 上门服务的地址不能为空
            Account account = orders.getAccount();
            orders.setProvinceId(account.getProvince_id());
            orders.setCityId(account.getCity_id());
            orders.setAreaId(account.getArea_id());
            orders.setAddress(account.getAddress());
			Integer provinceId = orders.getProvinceId();
			Integer cityId = orders.getCityId();
			Integer areaId = orders.getAreaId();
			if (provinceId == null || provinceId <= 0 || cityId == null
					|| cityId <= 0 || areaId == null || areaId <= 0) {
				throw new IllegalArgumentException("上门服务地址不能为空");
			}
			boolean flag = false;
			List<Address> list = addressService
					.findAddressByLevel(Constants.LEVEL_PROVINCE.toString());
			if (CollectionUtils.isNotEmpty(list)) {
				for (Address province : list) {
					if (province.getId().equals(provinceId)) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				throw new IllegalArgumentException("省份信息错误");
			}
			list.clear();
			list = addressService.findAddressByParentId(provinceId);
			flag = false;
			if (CollectionUtils.isNotEmpty(list)) {
				for (Address city : list) {
					if (city.getId().equals(cityId)) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				throw new IllegalArgumentException("城市信息错误");
			}
			list.clear();
			list = addressService.findAddressByParentId(cityId);
			flag = false;
			if (CollectionUtils.isNotEmpty(list)) {
				for (Address area : list) {
					if (area.getId().equals(areaId)) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				throw new IllegalArgumentException("地区信息错误");
			}
			if (StringUtils.isBlank(orders.getAddress())) {
				throw new IllegalArgumentException("详细地址不能为空");
			}
		}
//		if (orders.getServiceBegin() == null || orders.getServiceBegin() <= 0) {
//			throw new IllegalArgumentException("服务时间段不能为空");
//		}
		double cost = countService.hairDressingCountMoney(
				orders.getAnimalsId(), orders.getHairdressId());
		orders.setCost(cost);
        orders.setCreateTime(new Date());
		ordersDao.createOrder(orders);
	}
}
