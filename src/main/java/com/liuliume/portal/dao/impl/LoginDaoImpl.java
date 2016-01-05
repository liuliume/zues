package com.liuliume.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuliume.portal.dao.LoginDao;
import com.liuliume.portal.mybatis.mapper.LoginMapper;

@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public boolean login(String userName, String password) {
		return loginMapper.login(userName, password);
	}

}
