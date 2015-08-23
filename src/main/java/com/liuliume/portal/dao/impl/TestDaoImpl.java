package com.liuliume.portal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuliume.portal.dao.TestDao;
import com.liuliume.portal.mybatis.mapper.TestMapper;

@Repository
public class TestDaoImpl implements TestDao{

	@Autowired
	private TestMapper mapper;
	
	@Override
	public void add(String name, String passwd) {
		mapper.add(name, passwd);
	}

}
