package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.AddressDao;
import com.liuliume.portal.dao.cond.AddressQueryCond;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

	private Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public List<HashMap<String, Object>> list(Seed<HashMap<String,Object>> seed) throws Exception{
		logger.info("Address Service list bengins.");
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		AddressQueryCond cond = new AddressQueryCond();
		Map<String,Object> addressParams = new HashMap<String, Object>();
		
		if(seed.getFilter().containsKey("name")){
			String name = seed.getFilter().get("name");
            if(!StringUtils.isEmpty(name))
			    addressParams.put("name",name);
		}
        if(seed.getFilter().containsKey("parent_name")){
            String parent_name = seed.getFilter().get("parent_name");
            if(!StringUtils.isEmpty(parent_name))
                addressParams.put("parent_name",parent_name);
        }
		//add other query condition here
		
		cond.setAddressMap(addressParams);
		parameter.setCond(cond);
		
		int count = addressDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = addressDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

}
