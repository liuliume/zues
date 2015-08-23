package com.liuliume.portal.mybatis;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseSqlBuilder {
	private static Logger logger = LoggerFactory.getLogger(BaseSqlBuilder.class); 
	/**
	 * insert SQL
	 * @param entity
	 * @return
	 */
	public static String INSERT_SQL(Object entity) {
        BEGIN();
        Class<?> entityClass = entity.getClass();
        String tableName = null;
        if(entityClass.isAnnotationPresent(Table.class)){
        	Table tbAnno = entityClass.getAnnotation(Table.class);
        	tableName = tbAnno.name();
        }
        if(StringUtils.isBlank(tableName))
        	tableName = entityClass.getSimpleName().toUpperCase();
        INSERT_INTO(tableName);
        
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass); 
        for(PropertyDescriptor pd:descriptors){
        	String propertyName = pd.getName();
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter!=null){
        		Class<?> valueType = getter.getReturnType();
        		String columnName = null;
    			if(getter.isAnnotationPresent(Column.class)){
    				Column colAnno = getter.getAnnotation(Column.class);
    				columnName = colAnno.name();
    			}
    			if(StringUtils.isBlank(columnName))
    				columnName = propertyName.toUpperCase();
    			
        		if(getter.getName().equals("getClass"))
    				continue;
        		if(getter.isAnnotationPresent(Transient.class))
        			continue;
        		
        		if(getter.isAnnotationPresent(Id.class)){//ID列
        			if(getter.isAnnotationPresent(GeneratedValue.class)){
        				GeneratedValue generatedValue = getter.getAnnotation(GeneratedValue.class);
        				GenerationType generationType = generatedValue.strategy();        			
        				if(generationType == GenerationType.IDENTITY){
        					//DO NOTHING
        				}else if(generationType == GenerationType.TABLE){
        					VALUES(columnName, "#{"+propertyName+"}");
        				}else if(generationType == GenerationType.SEQUENCE){
        					VALUES(columnName, "#{"+propertyName+"}");
        				}else{
        					throw new RuntimeException("not support GenerationType AUTO yet");
        				}
        			}else{
        				VALUES(columnName, "#{"+propertyName+"}");
        			}
        		}else if(getter.isAnnotationPresent(JoinColumn.class)){//关联列
        			String joinColumnName = getter.getAnnotation(JoinColumn.class).name();
        			if(StringUtils.isBlank(joinColumnName))
        				joinColumnName = propertyName.toUpperCase();
    				if(valueType.isAnnotationPresent(Entity.class)){
    					Object propertyValue ;
    	    			try{
    	    				propertyValue = PropertyUtils.getSimpleProperty(entity, propertyName);
    	    				if(propertyValue != null){
        	        			VALUES(joinColumnName, "#{"+propertyName+"."+getIdProperty(valueType)+"}");
        	    			}
    		    		}catch(NoSuchMethodException e){
    		        		throw new RuntimeException(e);
    		        	} catch (IllegalAccessException e) {
    		        		throw new RuntimeException(e);
    					} catch (InvocationTargetException e) {
    						throw new RuntimeException(e);
    					}
    				}else{
    					logger.warn("not support yet...");
    				}
        		}else{//其他列
	    			if(valueType.isAnnotation())
						continue;
					if(valueType.isArray())
						continue;
					if(Collection.class.isAssignableFrom(valueType))
						continue;
					if(Map.class.isAssignableFrom(valueType))
						continue;
					
	    			Object propertyValue ;
	    			try{
	    				propertyValue = PropertyUtils.getSimpleProperty(entity, propertyName);
		    		}catch(NoSuchMethodException e){
		        		throw new RuntimeException(e);
		        	} catch (IllegalAccessException e) {
		        		throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
	    			if(propertyValue != null){
//	    				if(propertyValue instanceof Number){
//	    					if(valueType == int.class || valueType == long.class 
//	    							|| Integer.class.isAssignableFrom(valueType) || Long.class.isAssignableFrom(valueType)){
//	    						if(Constants.UNINIT_INT == ((Number)propertyValue).intValue())
//	    							continue;
//	    					}
//	    					if(valueType == double.class || valueType == float.class 
//	    							|| Double.class.isAssignableFrom(valueType) || Float.class.isAssignableFrom(valueType)){
//	    						if(Constants.UNINIT_DOUBLE == ((Number)propertyValue).doubleValue())
//	    							continue;
//	    					}
//						}
	        			VALUES(columnName, "#{"+propertyName+"}");
	    			}
        		}
        	}
        }
        return SQL();  
    }

	private static String getIdProperty(Class<?> type) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(type); 
		String idProperty = null;
		int idPropertyCount = 0;
        for(PropertyDescriptor pd:descriptors){
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter!=null && getter.isAnnotationPresent(Id.class)){
        		idProperty = pd.getName();
        		idPropertyCount++;
        	}
        }
        if(idPropertyCount == 0)
        	throw new RuntimeException("can not found id property for "+type);
        if(idPropertyCount >1)
        	throw new RuntimeException("only support single id property ");
        return idProperty;
	}
	/**
	 * 删除SQL
	 * @param entity
	 * @return
	 */
	public static String PHYSICAL_DELETE_SQL(Object entity){
		BEGIN();
		Class<?> entityClass = entity.getClass();
		String tableName = null;
		if(entityClass.isAnnotationPresent(Table.class)){
			Table tbAnno = entityClass.getAnnotation(Table.class);
			tableName = tbAnno.name();
		}
		if(StringUtils.isBlank(tableName))
			tableName = entityClass.getSimpleName().toUpperCase();
		DELETE_FROM(tableName);
		
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass);
		boolean idSpecified = false;
        for(PropertyDescriptor pd:descriptors){
        	String propertyName = pd.getName();
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter!=null && getter.isAnnotationPresent(Id.class)){
        		idSpecified = true;
        		String columnName = null;
    			if(getter.isAnnotationPresent(Column.class)){
    				Column colAnno = getter.getAnnotation(Column.class);
    				columnName = colAnno.name();
    			}
    			if(StringUtils.isBlank(columnName))
    				columnName = propertyName.toUpperCase();
        		WHERE(columnName +" = #{"+propertyName+"}");
//        		break;联合主键的情况需要指定多个属性
        	}
        }
        if(!idSpecified)
        	throw new RuntimeException("no id specified for entity");
		return SQL();
	}
	
	/**
	 * 删除SQL
	 * @param entity
	 * @return
	 */
	public static String LOGICAL_DELETE_SQL(Object entity){
		BEGIN();
		Class<?> entityClass = entity.getClass();
		String tableName = null;
		if(entityClass.isAnnotationPresent(Table.class)){
			Table tbAnno = entityClass.getAnnotation(Table.class);
			tableName = tbAnno.name();
		}
		if(StringUtils.isBlank(tableName))
			tableName = entityClass.getSimpleName().toUpperCase();
		UPDATE(tableName);
		
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass);
		boolean statusSpecified = false ,idSpecified = false ;
        for(PropertyDescriptor pd:descriptors){
        	String propertyName = pd.getName();
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter!=null){
        		String columnName = null;
    			if(getter.isAnnotationPresent(Column.class)){
    				Column colAnno = getter.getAnnotation(Column.class);
    				columnName = colAnno.name();
    			}
    			if(StringUtils.isBlank(columnName))
    				columnName = propertyName.toUpperCase();
    			if(getter.isAnnotationPresent(Id.class)){
    				idSpecified = true;
    				WHERE(columnName +" = #{"+propertyName+"}");
    			}
    			if(getter.isAnnotationPresent(Status.class)){
    				statusSpecified = true;
    				Status status = getter.getAnnotation(Status.class);
    				/*使用provider方式提供SQL,无法进行常量绑定,估计是mybatis3.0.5的bug,如果直接使用SQL绑定mapper无问题
    				SET(columnName + " = ${"+status.logic_delete_status()+"}");
    				*/
    				SET(columnName + " = "+status.logic_delete_status()+" ");
    			}
        	}
        }
        if(!idSpecified)
        	throw new RuntimeException("no id specified for entity");
        if(!statusSpecified)
        	throw new RuntimeException("no status specified for entity");
		return SQL();
	}
	
	/**
	 * 更新SQL
	 * 传入待更新的实体，需要为实体待更新的属性（对于表中待更新的字段）进行赋值即可，属性值如为null或系统定义得初始化int、double值，忽略该字段的更新
	 * @param entity
	 * @return
	 */
	public static String UPDATE_SQL(Object entity){
		BEGIN();
		Class<?> entityClass = entity.getClass();
		String tableName = null;
		if(entityClass.isAnnotationPresent(Table.class)){
			Table tbAnno = entityClass.getAnnotation(Table.class);
			tableName = tbAnno.name();
		}
		if(StringUtils.isBlank(tableName))
			tableName = entityClass.getSimpleName().toUpperCase();
		UPDATE(tableName);
		
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass);
		int setCount = 0;
		boolean idSpecified = false;
        for(PropertyDescriptor pd:descriptors){
        	String propertyName = pd.getName();
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter == null)
        		continue;
        	if(getter.getName().equals("getClass"))
				continue;
        	if(getter.isAnnotationPresent(Transient.class))
    			continue;
        	String columnName = null;
        	String propertyRefer = propertyName;
        	if(getter.isAnnotationPresent(JoinColumn.class)){
        		columnName = getter.getAnnotation(JoinColumn.class).name();
        	}else if(getter.isAnnotationPresent(Column.class)){
				columnName = getter.getAnnotation(Column.class).name();
			}
			if(StringUtils.isBlank(columnName))
				columnName = propertyName.toUpperCase();
    		if(getter.isAnnotationPresent(Id.class)){
    			idSpecified = true;
    			WHERE(columnName + " = #{"+propertyName+"}");
    			continue;
    		}
    		
    		Class<?> valueType = getter.getReturnType();
			if(valueType.isAnnotation())
				continue;
			if(valueType.isArray())
				continue;
			if(Collection.class.isAssignableFrom(valueType))
				continue;
			if(Map.class.isAssignableFrom(valueType))
				continue;
			
			Object propertyValue ;
			try{
				propertyValue = PropertyUtils.getSimpleProperty(entity, propertyName);
    		}catch(NoSuchMethodException e){
        		throw new RuntimeException(e);
        	} catch (IllegalAccessException e) {
        		throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			if(propertyValue != null){
				if(getter.isAnnotationPresent(JoinColumn.class)){//关联列
					if(!valueType.isAnnotationPresent(Entity.class)){
						logger.warn("not support yet...");
						continue;
					}
					String refrenceIdProperty;
					try {
						refrenceIdProperty = getIdProperty(valueType);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					} catch (NoSuchMethodException e) {
						throw new RuntimeException(e);
					}
					propertyRefer += "."+refrenceIdProperty;
	    		}else if(propertyValue instanceof Number){
//					if(valueType == int.class || valueType == long.class 
//							|| Integer.class.isAssignableFrom(valueType) || Long.class.isAssignableFrom(valueType)){
//						if(Constants.UNINIT_INT == ((Number)propertyValue).intValue())
//							continue;
//					}
//					if(valueType == double.class || valueType == float.class 
//							|| Double.class.isAssignableFrom(valueType) || Float.class.isAssignableFrom(valueType)){
//						if(Constants.UNINIT_DOUBLE == ((Number)propertyValue).doubleValue())
//							continue;
//					}
				}
				SET(columnName + " = #{"+propertyRefer+"}");
				setCount++;
			}
        }
        if(!idSpecified)
        	throw new RuntimeException("no id specified for entity");
		if(setCount == 0)
			throw new RuntimeException("update nothing...");
		return SQL();
	}
	
	/**
	 * 更新SQL
	 * 传入待更新的实体,与默认更新方法的区别：将所有未赋值属性（null）也更新为空
	 * @param entity
	 * @return
	 */
	public static String UPDATE_ENABLE_NULL_SQL(Object entity){
		BEGIN();
		Class<?> entityClass = entity.getClass();
		String tableName = null;
		if(entityClass.isAnnotationPresent(Table.class)){
			Table tbAnno = entityClass.getAnnotation(Table.class);
			tableName = tbAnno.name();
		}
		if(StringUtils.isBlank(tableName))
			tableName = entityClass.getSimpleName().toUpperCase();
		UPDATE(tableName);
		
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass);
		int setCount = 0;
		boolean idSpecified = false;
        for(PropertyDescriptor pd:descriptors){
        	String propertyName = pd.getName();
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter == null)
        		continue;
        	if(getter.getName().equals("getClass"))
				continue;
        	if(getter.isAnnotationPresent(Transient.class))
    			continue;
        	String columnName = null;
        	String propertyRefer = propertyName;
        	if(getter.isAnnotationPresent(JoinColumn.class)){
        		columnName = getter.getAnnotation(JoinColumn.class).name();
        	}else if(getter.isAnnotationPresent(Column.class)){
				columnName = getter.getAnnotation(Column.class).name();
			}
			if(StringUtils.isBlank(columnName))
				columnName = propertyName.toUpperCase();
    		if(getter.isAnnotationPresent(Id.class)){
    			idSpecified = true;
    			WHERE(columnName + " = #{"+propertyName+"}");
    			continue;
    		}
    		Class<?> valueType = getter.getReturnType();
			if(valueType.isAnnotation())
				continue;
			if(valueType.isArray())
				continue;
			if(Collection.class.isAssignableFrom(valueType))
				continue;
			if(Map.class.isAssignableFrom(valueType))
				continue;
			
			Object propertyValue ;
			try{
				propertyValue = PropertyUtils.getSimpleProperty(entity, propertyName);
    		}catch(NoSuchMethodException e){
        		throw new RuntimeException(e);
        	} catch (IllegalAccessException e) {
        		throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			if(propertyValue != null){
				if(getter.isAnnotationPresent(JoinColumn.class)){//关联列
					if(!valueType.isAnnotationPresent(Entity.class)){
						logger.warn("not support yet...");
						continue;
					}
					String joinColumnName = getter.getAnnotation(JoinColumn.class).name();
	    			if(StringUtils.isBlank(joinColumnName))
	    				joinColumnName = propertyName.toUpperCase();
					String refrenceIdProperty;
					try {
						refrenceIdProperty = getIdProperty(valueType);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					} catch (NoSuchMethodException e) {
						throw new RuntimeException(e);
					}
					propertyRefer += "."+refrenceIdProperty ; 
	    		}else if(propertyValue instanceof Number){
//					if(valueType == int.class || valueType == long.class 
//							|| Integer.class.isAssignableFrom(valueType) || Long.class.isAssignableFrom(valueType)){
//						if(Constants.UNINIT_INT == ((Number)propertyValue).intValue())
//							continue;
//					}
//					if(valueType == double.class || valueType == float.class 
//							|| Double.class.isAssignableFrom(valueType) || Float.class.isAssignableFrom(valueType)){
//						if(Constants.UNINIT_DOUBLE == ((Number)propertyValue).doubleValue())
//							continue;
//					}
				}
				SET(columnName + " = #{"+propertyRefer+"}") ;
				setCount++;
			}else{
				SET(columnName + " = null");
				setCount++;
			}
        }
        if(!idSpecified)
        	throw new RuntimeException("no id specified for entity");
		if(setCount == 0)
			throw new RuntimeException("update nothing...");
		return SQL();
	}
}
