package com.liuliume.portal.dao.impl;

import java.util.List;

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

}
