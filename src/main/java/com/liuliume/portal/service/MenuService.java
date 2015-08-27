package com.liuliume.portal.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuliume.portal.model.Menu;

@Service
public class MenuService {

	private Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Value("${user.home}/liuliume_portal/config/menu.json")
	private String menuFile ;

	private ObjectMapper mapper = new ObjectMapper();

	public List<Menu> getMenusAll() {
		String fname = expandFileName(menuFile);
		File file = new File(fname);
		Assert.isTrue(file.exists() , "菜单配置文件：" + fname + " 不存在！");
		String content;
		try {
			content = FileUtils.readFileToString(file);
		} catch (IOException e) {
			logger.error("读取菜单配置文件时异常！" ,e);
			throw new RuntimeException("读取配置文件：" + menuFile + " 异常！" + e.getMessage() , e);
		}
		Assert.isTrue(StringUtils.isNotBlank(content),"菜单配置文件" + fname + "没有内容！");;
		Menu m = null;
		try {
			m = mapper.readValue(file, Menu.class);
		} catch (Exception e) {
			logger.error("解析菜单配置文件：" + fname+" 异常！",e);
			throw new RuntimeException("解析菜单配置文件：" + fname+" 异常！",e);
		}
		return m.getChildren();
	}

	/**
	 * 将字符串中带有${...}的部分用其中的System.getProperty..来替换
	 * @author shiqiangguo
	 * @param fn
	 * @return
	 * @since JDK 1.6
	 */
	private String expandFileName(String fn ){
		Assert.notNull(fn, "空指针，配置文件为空！");
		String variable = StringUtils.substringBetween(fn, "${", "}");

		if (variable != null) {
			String v = System.getProperty(variable);

			Assert.notNull(v, "装载配置文件失败！系统属性：" + variable + " 不存在！配置文件："
					+ fn);
			String a = StringUtils.replace(fn, "${"+ variable +"}", v);
			logger.info("扩展文件名成功，原始配置： {} 扩展后的名字：{}", fn, a);
			fn = a;
		}
		return fn;
	}

}
