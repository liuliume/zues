package com.liuliume.portal.dao;

import java.util.List;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.Parameter;

public interface AccountDao {
	
	public int count(Parameter parameter);
	
	public List<Account> list(Parameter parameter);
	
	public void delete(Account account);
	
	public Account findAccountById(Integer account_id);
}
