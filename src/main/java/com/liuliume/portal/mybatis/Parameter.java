package com.liuliume.portal.mybatis;

import java.io.Serializable;

public class Parameter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String SORT_DESC = " desc ";
	public static final String SORT_ASC = " asc ";

	/**
	 * 总记录数
	 */
	private int allCount;// = MyBatisConstants.UNINIT_INT;

	/**
	 * LIMIT 结束
	 */
	private int limit;// = MyBatisConstants.UNINIT_INT;

	/**
	 * LIMIT 开始
	 */
	private int start;// = MyBatisConstants.UNINIT_INT;

	/**
	 * 需排序的列名
	 */
	private String orderby = null;
	/**
	 * 升序还是降序
	 */
	private String sort = null;

	/**
	 * 联合查询条件
	 */
	private transient QueryCond cond = null;

	/**
	 * 每页显示的个数
	 */
	private int pageSize = 20;
	/**
	 * 当前页码
	 */
	private int currPage = 1;
	

	public Parameter() {

	}
	
	/**
	 * 总页数
	 */
	private int pageCount;// = MyBatisConstants.UNINIT_INT;

	public Parameter(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	public QueryCond getCond() {
		return cond;
	}

	public void setCond(QueryCond cond) {
		this.cond = cond;
	}
	
//	private transient ProductsView productsView = null;


	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

}
