package com.liuliume.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AccountDao;
import com.liuliume.portal.dao.cond.AccountQueryCond;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.AccountMapper;
import com.liuliume.portal.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public List<Account> list(Seed<Account> seed) throws Exception{
		logger.info("Account Service list bengins.");
		List<Account> result = new ArrayList<Account>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		AccountQueryCond cond = new AccountQueryCond();
		Account account = new Account();
		
		if(seed.getFilter().containsKey("nameQ")){
			String name = seed.getFilter().get("nameQ");
			account.setUniqname(name);
		}
		//add other query condition here
		
		cond.setAccount(account);
		parameter.setCond(cond);
		
		int count = accountDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = accountDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

	@Override
	@Transactional
	public void batchDelete(String account_ids) throws Exception {
		String[] aids = account_ids.split(",");
		for (String aid : aids) {
			Account account = new Account();
			account.setAccount_id(new Integer(aid));
			accountDao.delete(account);
		}
	}

	@Override
	public Account findAccountById(Integer account_id) throws Exception {
		Account account = null;
		if(account_id!=null)
			account = accountDao.findAccountById(account_id);
		return account;
	}

	@Override
	public void createOrUpdate(Account account) throws Exception {
		if(account==null)
			throw new IllegalArgumentException("Account为空");
		if(account.getAccount_id()==null){//add
			accountDao.createAccount(account);
		}else {
			accountDao.updateAccount(account);
		}
	}

}
