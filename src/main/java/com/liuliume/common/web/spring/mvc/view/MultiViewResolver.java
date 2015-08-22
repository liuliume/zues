package com.liuliume.common.web.spring.mvc.view;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
/**
 * 多个视图解析适配
 * @author shiqiangguo
 *
 */
public class MultiViewResolver implements ViewResolver {  
	  
    private Map<String, ViewResolver> resolvers;  
  
    @Override  
    public View resolveViewName(String viewName, Locale locale) throws Exception {  
        int n = viewName.lastIndexOf("."); // 获取  
                                            // viewName(modelAndView中的名字)看其有没有下划线  
        String suffix = "";  
  
        // 没有默认使用“jsp“方式 解析,有的话截取下划线后面的字符串 这里一般是jsp,ftl,vm与配置文件中的<entry  
        // key="ftl">的key匹配  
        if (n == (-1)) {
            suffix = "jsp";  
        }  
        else {  
            suffix = viewName.substring(n + 1);  
            // 取下划线前面的部分 那时真正的资源名.比如我们要使用hello.jsp 那viewName就应该是hello_jsp  
            viewName = viewName.substring(0, n);  
        }  
  
        // 根据下划线后面的字符串去获取托管的视图解析类对象  
        ViewResolver resolver = resolvers.get(suffix);
        if (resolver != null) {  
            return resolver.resolveViewName(viewName, locale);  
        }  
        else {  
            return null;  
        }  
    }  
  
    public Map<String, ViewResolver> getResolvers() {  
        return resolvers;  
    }  
  
    public void setResolvers(Map<String, ViewResolver> resolvers) {  
        this.resolvers = resolvers;  
    }  
}  