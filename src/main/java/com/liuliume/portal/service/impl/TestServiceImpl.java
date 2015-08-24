package com.liuliume.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuliume.portal.dao.TestDao;
import com.liuliume.portal.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDao testDao;
	
	
	@Override
	@Transactional
	public void add(String name,String passwd) throws Exception{
		testDao.add(name, passwd);
		if("admin".equals(name))
			throw new RuntimeException("Test Transactional");
	}
}
