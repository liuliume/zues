package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AddressService {
	
	/**
	 * 查询Address表
	 * @param seed
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> list(Seed<HashMap<String,Object>> seed) throws Exception;

    public Address findAddressById(String address_id) throws Exception;

    public List<Address> findAddressByLevel(String level) throws Exception;

    public List<Address> findAddressByParentId(int parent_id) throws Exception;
}
