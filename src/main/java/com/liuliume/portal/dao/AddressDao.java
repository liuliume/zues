package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.Parameter;

import java.util.List;

public interface AddressDao {
	
	public int count(Parameter parameter);
	
	public List<Address> list(Parameter parameter);
}
