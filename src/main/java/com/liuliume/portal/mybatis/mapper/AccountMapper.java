package com.liuliume.portal.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.mybatis.Parameter;

public interface AccountMapper {

	int count(@Param("param")Parameter parameter);
	
	public List<Account> list(@Param("param")Parameter parameter);
}
