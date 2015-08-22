package com.liuliume.portal.mybatis;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.ibatis.builder.xml.dynamic.DynamicSqlSource;
import org.apache.ibatis.builder.xml.dynamic.MixedSqlNode;
import org.apache.ibatis.builder.xml.dynamic.SqlNode;
import org.apache.ibatis.builder.xml.dynamic.TextSqlNode;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

@Intercepts(@Signature(method = "update", type = StatementHandler.class, args = { Statement.class }))
public class IdentityGetter implements Interceptor {
	private static final String SELECT_IDENTITY = "!SelectIdentity";
	private String selectStatement ; 

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object returnObj = invocation.proceed();
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		BaseStatementHandler delegate = (BaseStatementHandler)FieldUtils.getField(RoutingStatementHandler.class, "delegate", true).get(handler);
		MappedStatement mappedStatement = (MappedStatement)FieldUtils.getField(BaseStatementHandler.class, "mappedStatement", true).get(delegate);  
		
		if(mappedStatement.getSqlCommandType() == SqlCommandType.INSERT){//只有insert操作才进行id获取
			Object parameter = delegate.getBoundSql().getParameterObject();
			if(parameter!=null && shouldInterceptor(parameter.getClass())){
				Executor executor = (Executor)FieldUtils.getField(BaseStatementHandler.class, "executor", true).get(delegate);
				processGeneratedKeys(mappedStatement.getConfiguration(), executor.getTransaction(), parameter);
			}
		}
		return returnObj;
	}

	private boolean shouldInterceptor(Class<?> entityClass){
		if(!entityClass.isAnnotationPresent(Entity.class))
			return false;
		
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass);
		boolean idSpecified = false;
		boolean identifyGenerator = false;
        for(PropertyDescriptor pd:descriptors){
        	Method getter = PropertyUtils.getReadMethod(pd);
        	if(getter!=null && getter.isAnnotationPresent(Id.class)){
        		idSpecified = true;
        		if(getter.isAnnotationPresent(GeneratedValue.class)){
	        		GeneratedValue generatedValue = getter.getAnnotation(GeneratedValue.class);
    				GenerationType generationType = generatedValue.strategy();        			
    				if(generationType == GenerationType.IDENTITY){
    					identifyGenerator = true;
    				}
        		}
        		break;
        	}
        }
        return idSpecified && identifyGenerator;
	}
	
	private void processGeneratedKeys(final Configuration configuration, Transaction transaction, Object parameter) {
		try {
			MappedStatement keyStatement = MappedStatementGenerator.getInstance(configuration, selectStatement).genKeyStatement(parameter.getClass());
			String keyProperty = keyStatement.getKeyProperty();
			final MetaObject metaParam = configuration.newMetaObject(parameter);
			if (keyProperty != null && metaParam.hasSetter(keyProperty)) {
				Executor keyExecutor = configuration.newExecutor(transaction, ExecutorType.SIMPLE);
				List<?> values = keyExecutor.query(keyStatement, parameter,RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
				if (values.size() > 1) {
					throw new ExecutorException(
							"Select statement for SelectKeyGenerator returned more than one value.");
				}
				metaParam.setValue(keyProperty, values.get(0));
			}
		} catch (Exception e) {
			throw new ExecutorException(
					"Error selecting key or setting result to parameter object. Cause: "
							+ e, e);
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.selectStatement = properties.getProperty("statement");
	}

	private static class MappedStatementGenerator{
		
		private final Configuration configuration;
		private final String statement;
		
		private MappedStatementGenerator(Configuration configuration,String statement){
			this.configuration = configuration;
			this.statement = statement ;
		}
		
		private static MappedStatementGenerator getInstance(Configuration configuration, String statement){
			return new MappedStatementGenerator(configuration,statement);
		}
		
		public MappedStatement genKeyStatement(Class<?> entityClass) {
			SqlSource sqlSource = buildSqlSourceFromStrings();
			String id = entityClass.getName() + SELECT_IDENTITY;//待修改，这个就会跟mapper的命名空间不太一致
			
			MappedStatement.Builder statementBuilder = new MappedStatement.Builder(
					configuration, 
					id, 
					sqlSource, 
					SqlCommandType.SELECT);
			statementBuilder.keyProperty(getKeyProperty(entityClass));
			statementBuilder.resultMaps(getResultMaps(statementBuilder));
			MappedStatement statement = statementBuilder.build();
			return statement;
		}

		private List<ResultMap> getResultMaps(MappedStatement.Builder statementBuilder){
			ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(
					configuration, statementBuilder.id() + "-Inline", int.class,
					new ArrayList<ResultMapping>());
			List<ResultMap> resultMaps = new ArrayList<ResultMap>();
			resultMaps.add(inlineResultMapBuilder.build());
			return resultMaps;
		}
		
		private String getKeyProperty(Class<?> entityClass){
			PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(entityClass); 
	        for(PropertyDescriptor pd:descriptors){
	        	String propertyName = pd.getName();
	        	Method getter = PropertyUtils.getReadMethod(pd);
	        	if(getter!=null && getter.isAnnotationPresent(Id.class)){
	        		return propertyName;
	        	}
	        }
	        throw new RuntimeException("no identifier specified for entity");
		}
		
		private SqlSource buildSqlSourceFromStrings() {
			String[] strings = new String[]{statement};
			final StringBuilder sql = new StringBuilder();
			for (String fragment : strings) {
				sql.append(fragment);
				sql.append(" ");
			}
			ArrayList<SqlNode> contents = new ArrayList<SqlNode>();
			contents.add(new TextSqlNode(sql.toString()));
			MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
			return new DynamicSqlSource(configuration, rootSqlNode);
		}
		
	};
}
