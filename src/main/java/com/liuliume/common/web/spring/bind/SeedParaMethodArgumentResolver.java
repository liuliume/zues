package com.liuliume.common.web.spring.bind;

import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.liuliume.common.pagination.PageFilter;
import com.liuliume.common.pagination.Seed;
import com.liuliume.common.pagination.SeedParameter;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;

/**
 * 查询参数封装
 *
 */
public class SeedParaMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(SeedParam.class))
			return true;
		return false;
	}

	/**
	 * The arguments could be "page", "pagesize", "order" or the filters.
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		@SuppressWarnings("rawtypes")
		Seed seed = new Seed();
		PageFilter pageFilter = new PageFilter();

		for (Iterator<String> paramNameItr = webRequest.getParameterNames(); paramNameItr
				.hasNext();) {
			String paramName = paramNameItr.next();
			if (paramName.equals(SeedParameter.PAGE_NUMBER)) {
				seed.setPageNumber(Integer.parseInt(webRequest
						.getParameter(paramName)));
			} else if (paramName.equals(SeedParameter.PAGE_SIZE)) {
				seed.setPageSize(Integer.parseInt(webRequest
						.getParameter(paramName)));
			} else if (paramName.equals(SeedParameter.ORDER)) {
				seed.setOrder(webRequest.getParameter(paramName));
			} else {
				pageFilter.add(paramName, webRequest.getParameter(paramName));
			}
		}

		seed.setPageFilter(pageFilter);
		return seed;
	}

}
