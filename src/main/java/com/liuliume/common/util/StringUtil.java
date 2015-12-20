package com.liuliume.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.collections.MapUtils;

public class StringUtil {

	private static String string = "abcdefghijklmnopqrstuvwxyz0123456789";
	public static String genRandomString(int num){
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			sb.append(string.charAt(random.nextInt(len)));
		}
		return sb.toString();
	}
	
	public static String createParam(Map<String, String> map){
		StringBuffer sb = new StringBuffer();
		if(MapUtils.isNotEmpty(map)){
			for (Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				sb.append(key+"="+value+"&");
			}
		}
		String result = sb.toString();
		result=result.substring(0,result.length()-1);
		return result;
	}
	
	public static String map2xml(Map<String, String> map){
		if(MapUtils.isEmpty(map)){
			return "";
		}
		String xml = "<xml>";
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			xml += "<" + key + ">" + val + "</" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}
}
