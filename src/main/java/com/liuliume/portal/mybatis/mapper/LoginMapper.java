package com.liuliume.portal.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

public interface LoginMapper {

	public boolean login(@Param("name") String userName,
			@Param("passwd") String password);
}
