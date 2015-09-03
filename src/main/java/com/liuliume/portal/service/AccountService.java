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
	
	/**
	 * 批量删除Account列表
	 * @param account_ids
	 * @throws Exception
	 */
	public void batchDelete(String account_ids) throws Exception;
	
	public Account findAccountById(Integer account_id) throws Exception;
	
	public void createOrUpdate(Account account) throws Exception;
	
	public List<Account> listAllAccount();
}
