package com.liuliume.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liuliume.common.util.RedisUtils;

public class TestJedis {
	
	private ApplicationContext app;
	
	@Before
	public void init(){
		app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void testSet(){
		System.out.println(app);
		RedisUtils utils = (RedisUtils) app.getBean("redisUtils");
		try {
			utils.setWithinSeconds("name", "xiayun", 1);
			String name = utils.get("name");
			System.out.println(name);
			Thread.sleep(2000);
			name=utils.get("name");
			System.out.println(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
