package com.liuliume.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.liuliume.portal.common.Constants;


public class SignUtil {

	public static String getSign(Map<String, Object> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Constants.PARTNER_KEY;
        //Util.log("Sign Before MD5:" + result);
        result = MD5Util.MD5(result).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
//		map.put(key, value)
	}
}
