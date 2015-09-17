package com.liuliume.portal.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuliume.portal.dao.AccountDao;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.AccountMapper;

@Repository
public class AccountDaoImpl implements AccountDao{

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public int count(Parameter parameter) {
		return accountMapper.count(parameter);
	}

	@Override
	public List<Account> list(Parameter parameter) {
		return accountMapper.list(parameter);
	}

	@Override
	public void delete(Account account) {
		accountMapper.deleteEntityLogically(account);
	}

	@Override
	public Account findAccountById(Integer account_id) {
		return accountMapper.findAccountById(account_id);
	}

    @Override
    public Account findAccountByMobile(String mobile) {
        return accountMapper.findAccountByMobile(mobile);
    }

    @Override
	public void createAccount(Account account) {
        if(StringUtils.isEmpty(account.getMobile())){
           account.setPassport_id(account.getMobile() + "@liuliume.com");
        }
		accountMapper.createEntity(account);
	}

	@Override
	public void updateAccount(Account account) {
		accountMapper.updateEntity(account);
	}

	@Override
	public List<Account> listAllAccount() {
		return accountMapper.listAllAccount();
	}

}
