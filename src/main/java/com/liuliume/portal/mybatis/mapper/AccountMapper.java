package com.liuliume.portal.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;

public interface AccountMapper extends MyBatisBaseMapper<Account>{

	int count(@Param("param")Parameter parameter);
	
	public List<Account> list(@Param("param")Parameter parameter);
	
	public Account findAccountById(@Param("account_id")Integer account_id);
	
	public List<Account> listAllAccount();
}
