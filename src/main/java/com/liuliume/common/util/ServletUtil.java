package com.liuliume.common.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class ServletUtil {

    public static String defaultDomain = "localhost";

    public static String getParameterString(HttpServletRequest request) {
        StringBuilder requestParam = new StringBuilder();
        Map map = request.getParameterMap();
        for (Object key : map.keySet().toArray()) {
            requestParam.append(key.toString());
            requestParam.append(":");
            requestParam.append(request.getParameter(key.toString()));
            requestParam.append(",");
        }
        return requestParam.toString();
    }



	/* ------------------------- cookie ------------------------- */

    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) return cookie.getValue();
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String key, String value, int second, String domain) {
        saveCookie(response, key, value, second, "/", domain);
    }

    public static void setCookie(HttpServletResponse response, String key, String value, int second) {
        saveCookie(response, key, value, second, "/");
    }

    public static void saveCookie(HttpServletResponse response, String key, String value,
                                  int second, String path) {
        saveCookie(response, key, value, second, path, defaultDomain);
    }

    public static void saveCookie(HttpServletResponse response, String key, String value,
                                  int second, String path, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(second); // 默认为-1,
        response.addCookie(cookie);
    }

    public static void clearCookie(HttpServletResponse response, String key) {
        clearCookie(response, key, 0, "/", defaultDomain);
    }

    public static void clearCookie(HttpServletResponse response, String key, String domain) {
        clearCookie(response, key, 0, "/", domain);
    }


    public static void clearCookie(HttpServletResponse response, String key, int second,
                                   String path, String domain) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setMaxAge(second);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

}
