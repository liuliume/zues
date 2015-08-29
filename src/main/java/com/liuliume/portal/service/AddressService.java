package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;

import java.util.List;

public interface AddressService {
	
	/**
	 * 查询Address表
	 * @param seed
	 * @throws Exception
	 */
	public List<Address> list(Seed<Address> seed) throws Exception;
}
