package com.liuliume.portal.service;

import java.util.List;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Account;

public interface AccountService {
	
	/**
	 * 查询Account列表
	 * @param seed
	 * @throws Exception
	 */
	public List<Account> list(Seed<Account> seed) throws Exception;
}
