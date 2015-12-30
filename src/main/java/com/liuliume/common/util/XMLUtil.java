package com.liuliume.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.collections.MapUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class XMLUtil {

	public static Map<String, Object> xml2Map(String xml) throws Exception {
		org.dom4j.Document document = DocumentHelper.parseText(xml);
		org.dom4j.Element rootElement = document.getRootElement();
		Map<String, Object> map = new HashMap<String, Object>();
		ele2map(map, rootElement);
		System.out.println(map);
		return map;
	}

	@SuppressWarnings("unchecked")
	private static void ele2map(Map<String, Object> map, org.dom4j.Element ele) {
		System.out.println(ele);
		// 获得当前节点的子节点
		List<org.dom4j.Element> elements = ele.elements();
		if (elements.size() == 0) {
			// 没有子节点说明当前节点是叶子节点，直接取值即可
			map.put(ele.getName(), ele.getText());
		} else if (elements.size() == 1) {
			// 只有一个子节点说明不用考虑list的情况，直接继续递归即可
			Map<String, Object> tempMap = new HashMap<String, Object>();
			ele2map(tempMap, elements.get(0));
			map.put(ele.getName(), tempMap);
		} else {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (org.dom4j.Element element : elements) {
				tempMap.put(element.getName(), null);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = elements.get(0).getNamespace();
				List<org.dom4j.Element> elements2 = ele.elements(new QName(
						string, namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Map> list = new ArrayList<Map>();
					for (org.dom4j.Element element : elements2) {
						Map<String, Object> tempMap1 = new HashMap<String, Object>();
						ele2map(tempMap1, element);
						list.add(tempMap1);
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					Map<String, Object> tempMap1 = new HashMap<String, Object>();
					ele2map(tempMap1, elements2.get(0));
					map.put(string, tempMap1.get(string));
				}
			}
		}
	}

	public static String map2Xml(Map<String, String> map) {
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Pattern pattern =Pattern.compile("[0-9]*");
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append("<" + key + ">");
			if(pattern.matcher(value).matches()){
				sb.append(value);
			}
			else {
				sb.append("<![CDATA["+value+"]]>");
			}
			sb.append("</" + key + ">");
		}
		sb.append("</xml>");
		try {
//			return new String(sb.toString().getBytes(), "ISO-8859-1");
			return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String string = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx3382d54a842ce919]]></appid><mch_id><![CDATA[1289131601]]></mch_id><nonce_str><![CDATA[UHrevQvNca3f2gqd]]></nonce_str><sign><![CDATA[AD2C242F99FADCAB2D87535ED7A9054F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201512261121533b857c30590693270307]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml>";
		// Map<String, String> map = getMapFromXML(string);
		Map<String, Object> map = xml2Map(string);
		System.out.println(map);
		
		System.out.println("-----------");
		String s = "宠物训练";
		String s1 = new String(s.getBytes("ISO_8859-1"),"utf-8");
		System.out.println(s1);
	}
}
