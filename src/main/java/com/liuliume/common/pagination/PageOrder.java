package com.liuliume.common.pagination;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * An PageOrder object that holds a order field and a order direction. 
 * <p>Order direction should be ASC or DESC</p>
 * @author limingxing
 *
 */
public class PageOrder implements Serializable {

	private static final long serialVersionUID = -6128934348339502848L;
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	private String name = StringUtils.EMPTY;

	private boolean asc = true;
	
	public boolean isEmpty() {
		return StringUtils.isBlank(name);
	}

	public String getName() {
		return name;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public void parser(String expressions) {
		String[] array = expressions.split("==");
		this.name = array[0];

		if (StringUtils.equalsIgnoreCase("asc", array[1])) {
			this.asc = true;
		} else {
			this.asc = false;
		}
	}
	
	public String getOrderExpressions() {
		return getName() + "==" + (isAsc() ? ASC : DESC);
	}
	
	public void setOrder(String expressions) {
		parser(expressions);
	}

	public String getOrder() {
		return getName() + " " + (isAsc() ? ASC : DESC);
	}
	
	public String getOrderDirection() {
		return (isAsc() ? ASC : DESC);
	}

	public void setDefault(String expressions) {
		if (isEmpty()) {
			parser(expressions);
		}
	}
}
