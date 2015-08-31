package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.mybatis.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AddressDao {
	
	public int count(Parameter parameter);
	
	public List<HashMap<String, Object>> list(Parameter parameter);

    public Address findAddressById(Integer address_id);

    public List<Address> findAddressByLevel(String level);

    public List<Address> findAddressByParentId(int parent_id);

    public void createAddress(Address address);

    public void updateAddress(Address address);

    public void delete(Address address);

}
