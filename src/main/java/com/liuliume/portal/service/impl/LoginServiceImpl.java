package com.liuliume.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuliume.portal.dao.LoginDao;
import com.liuliume.portal.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public boolean login(String userName, String password) {
		return loginDao.login(userName, password);
	}

}
