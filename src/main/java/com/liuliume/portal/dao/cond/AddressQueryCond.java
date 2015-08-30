package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.QueryCond;

import java.util.Map;

public class AddressQueryCond implements QueryCond{
	
	private Map<String,Object> addressMap;

	public Map<String,Object> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String,Object> addressMap) {
		this.addressMap = addressMap;
	}
	
}
