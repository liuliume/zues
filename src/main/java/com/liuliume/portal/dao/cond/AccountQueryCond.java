package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.QueryCond;

public class AccountQueryCond implements QueryCond{
	
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
