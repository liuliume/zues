package com.liuliume.portal.mybatis;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;


public abstract interface MyBatisBaseMapper<T> {
	@InsertProvider(type = BaseSqlBuilder.class, method = "INSERT_SQL")
	public void createEntity(T entity);
	
	@UpdateProvider(type = BaseSqlBuilder.class, method = "LOGICAL_DELETE_SQL")
	public void deleteEntityLogically(T entity);
	
	@DeleteProvider(type = BaseSqlBuilder.class, method = "PHYSICAL_DELETE_SQL")
	public void deleteEntityPhysically(T entity);
	
	@UpdateProvider(type = BaseSqlBuilder.class, method = "UPDATE_SQL")
	public void updateEntity(T entity);
}