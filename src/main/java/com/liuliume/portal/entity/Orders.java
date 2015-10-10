package com.liuliume.portal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.liuliume.portal.common.Constants;
import com.liuliume.portal.model.OrderStatusEnum;
import com.liuliume.portal.model.OrderTypeEnum;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String orderId;

	/**
	 * 订单账户
	 */
	private Integer accountId;

	/**
	 * 订单类型,1-寄样,2-训练,3-美容
	 */
	private Integer orderType;

	/**
	 * 宠物id
	 */
	private Integer animalsId;

	private Integer provinceId;

	private Integer cityId;

	private Integer areaId;

	private String address;

	/**
	 * 服务方式:0-上门服务,1-到店服务
	 */
	private Integer serviceType;

	/**
	 * 服务开始时间:如9:00
	 */
	private Integer serviceBegin;

	/**
	 * 服务结束时间如11:00
	 */
	private Integer serviceEnd;

	/**
	 * 服务开始日期 yyyy-MM-dd
	 */
	private String startDate;

	/**
	 * 服务结束时间 yyyy-MM-dd
	 */
	private String endDate;

	/**
	 * 付款状态:0-未付款,1-已付款
	 */
	private Integer paymentStatus;

	/**
	 * 订单状态
	 */
	private Integer status;

	/**
	 * 订单中房间类型
	 */
	private Integer roomId;

	/**
	 * 订单中课程编号
	 */
	private Integer courseId;

	/**
	 * 订单金额
	 */
	private Double cost;

	/**
	 * 下单时间
	 */
	private Date createTime;

	/**
	 * 最近更新时间
	 */
	private Date lastModified;

	private String animalName;

	private Account account;

	private OrderTypeEnum orderTypeEnum;

	private OrderStatusEnum statusEnum;

    private String orderStatusEnumDesc;

	private String serviceTypeDesc;

	private Room room;

	private Course course;

	private String serviceTime;

	private String paymentStatusDesc;
	
	/**
	 * 服务项目
	 */
	private Integer hairdressId;
	
	private Hairdressing hairdressing;

    private String province;

    private String city;

    private String area;
	
	/**
	 * 支付方式
	 */
	private Integer paymentType;

	@Column(name="payment_type")
	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	@Id
	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "account_id")
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "order_type")
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Column(name = "animals_id")
	public Integer getAnimalsId() {
		return animalsId;
	}

	public void setAnimalsId(Integer animalsId) {
		this.animalsId = animalsId;
	}

	@Column(name = "province_id")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "city_id")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "area_id")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "service_type")
	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	@Column(name = "service_begin")
	public Integer getServiceBegin() {
		return serviceBegin;
	}

	public void setServiceBegin(Integer serviceBegin) {
		this.serviceBegin = serviceBegin;
	}

	@Column(name = "service_end")
	public Integer getServiceEnd() {
		return serviceEnd;
	}

	public void setServiceEnd(Integer serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	@Column(name = "start_date")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "payment_status")
	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "room_id")
	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	@Column(name = "course_id")
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "cost")
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_modified")
	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Transient
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Transient
	public OrderTypeEnum getOrderTypeEnum() {
		OrderTypeEnum typeEnum = OrderTypeEnum.parse(this.orderType);
		return typeEnum;
	}

	@Transient
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Transient
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Transient
	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	@Transient
	public OrderStatusEnum getStatusEnum() {
		return OrderStatusEnum.parse(status);
	}

    @Transient
    public String getOrderStatusEnumDesc() {
        return OrderStatusEnum.parse(status).getDesc();
    }

    @Transient
	public String getServiceTime() {
//		String date = "";
//		String start = startDate.trim();
//		String end = endDate.trim();
//		if (start.equals(end)) {
//			date = start;
//		} else {
//			date = start + "~" + end;
//		}
//		String time = "";
//		if(serviceBegin!=null){
//			time+=serviceBegin+":00 ";
//		}
//		if(serviceEnd!=null){
//			time+="-" +serviceEnd+":00 ";
//		}
//		return date + " " +time;
        return null;
	}

	@Transient
	public String getServiceTypeDesc() {
		if (serviceType != null) {
			if (serviceType == Constants.SERVICE_DOOR)
				return "上门服务";
			if (serviceType == Constants.SERVICE_SHOP)
				return "到店服务";
		}
		return "";
	}

	@Transient
	public String getPaymentStatusDesc() {
		if (paymentStatus == Constants.PAYMENT_NO)
			return "未付款";
		return "已付款";
	}
	
	@Column(name="hairdress_id")
	public Integer getHairdressId() {
		return hairdressId;
	}

	public void setHairdressId(Integer hairdressId) {
		this.hairdressId = hairdressId;
	}

	@Transient
	public Hairdressing getHairdressing() {
		return hairdressing;
	}

	public void setHairdressing(Hairdressing hairdressing) {
		this.hairdressing = hairdressing;
	}

    @Transient
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Transient
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Transient
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}