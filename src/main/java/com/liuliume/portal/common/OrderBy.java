package com.liuliume.portal.common;


public class OrderBy {
	public static final String DESC = "DESC";
	public static final String ASC = "ASC";
	private String field;
	private String order = DESC;
	public OrderBy(){
	}
	public OrderBy(String field){
		this.field = field;
	}
	public OrderBy(String field , String order){
		this(field);
		this.order = order.toUpperCase();
	}

	/**
	 * 得到原始的列名
	 * @return
	 */
    public String getOriginField(){
        return field;
    }

    /**
	 * 得到转换驼峰为下划线的列名
	 * @return
	 */
	public String getField() {
		return MBox.camelizeToUnderLine(field);
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String toString(){
		return "OrderBy["+ field+":" + order+"]";
	}
	
}
