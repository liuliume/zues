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
import com.liuliume.portal.mybatis.Status;

@Entity
@Table(name="account")
/**
 * @author xiayun
 *
 */
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer account_id;
	private String passport_id;
	private String email;
	private String mobile;
	private Date reg_time;//注册时间
	private String reg_ip;//注册IP
	/**
	 * 账号类型,1-正式用户 2-登陆账户未激活 3-封禁用户
	 */
	private int flag;
	private String uniqname;//昵称
	private int gender;
	private int province_id;
	private int city_id;
	private int area_id;
	private String address;
	
	private String province;
	private String city;
	private String area;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id")
	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	@Column(name="passport_id")
	public String getPassport_id() {
		return passport_id;
	}

	public void setPassport_id(String passport_id) {
		this.passport_id = passport_id;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="reg_time")
	public Date getReg_time() {
		return reg_time;
	}

	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}

	@Column(name="reg_ip")
	public String getReg_ip() {
		return reg_ip;
	}

	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}

	@Column(name="flag")
	@Status(logic_delete_status=Constants.SIMPLE_DELETE)
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Column(name="uniq_name")
	public String getUniqname() {
		return uniqname;
	}

	public void setUniqname(String uniqname) {
		this.uniqname = uniqname;
	}

	@Column(name="gender")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name="province_id")
	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}

	@Column(name="city_id")
	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	@Column(name="area_id")
	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Transient
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Transient
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Transient
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", passport_id="
				+ passport_id + ", email=" + email + ", mobile=" + mobile
				+ ", reg_time=" + reg_time + ", reg_ip=" + reg_ip + ", flag="
				+ flag + ", uniqname=" + uniqname + ", gender=" + gender
				+ ", province_id=" + province_id + ", city_id=" + city_id
				+ ", area_id=" + area_id + ", address=" + address + "]";
	}
}
