package com.liuliume.portal.dao.impl;

import com.liuliume.portal.dao.AddressDao;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AddressDaoImpl implements AddressDao{

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public int count(Parameter parameter) {
		return addressMapper.count(parameter);
	}

	@Override
	public List<HashMap<String, Object>> list(Parameter parameter) {
		return addressMapper.list(parameter);
	}

    @Override
    public Address findAddressById(String address_id) {
        return addressMapper.findAddressById(address_id);
    }


}
