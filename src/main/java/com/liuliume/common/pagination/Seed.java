package com.liuliume.common.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>A seed should contain a collection of result and the attributes which hold the parameters for generate pagination.</p>
 * <p>Support url like <b>"inquiries/everyone?pagesize=10&page=8&order=lsin==desc&q=s"</b></p>
 * 
 * @author xiayun
 * 
 * @see com.liuliu.common.pagination.PageFilter
 * @see com.liuliu.common.pagination.PageOrder
 */
public class Seed<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 8673524087207936999L;
	
	private Integer pageNumber = 1;

	private Integer pageSize = 10;

	private PageFilter pageFilter = new PageFilter();

	private PageOrder pageOrder = new PageOrder();
	
	private Long totalSize = 0L;

	private List<T> result = new ArrayList<T>();
	
	private String actionPath = StringUtils.EMPTY;

	private String queryStr = StringUtils.EMPTY;
	
	private Map<String, String> filter = null;

	public Seed() {
	}

	public Seed(Integer pageNumber, Integer pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public Seed(Integer pageNumber, Integer pageSize, PageFilter pageFilter, PageOrder pageOrder) {
		this(pageNumber, pageSize);
		this.pageFilter = pageFilter;
		this.pageOrder = pageOrder;
	}

	/**
	 *  The page number attribute is the current page number.
	 */
	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		if (pageNumber < 1) {
			this.pageNumber = 1;
		}
	}

	/**
	 * The page size per page
	 */
	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if (pageSize < 1) {
			this.pageSize = 10;
		}
	}

	/**
	 * The page filter holds lots of filters.
	 */
	public PageFilter getPageFilter() {
		return pageFilter;
	}

	public void setPageFilter(PageFilter pageFilter) {
		this.pageFilter = pageFilter;
		this.queryStr = pageFilter.toString();
	}
	
	public Map<String, String> getFilter() {
		this.filter = pageFilter.getFilters();
		return this.filter;
	}

	/**
	 * The page order hold the order attribute.
	 */
	public PageOrder getPageOrder() {
		return pageOrder;
	}

	public void setPageOrder(PageOrder pageOrder) {
		this.pageOrder = pageOrder;
	}

	/**
	 * The result list will be set into a model for generate datatable html code to client.
	 */
	public List<T> getResult() {
		return this.result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * The total size is the size of all records are satisfied with filters.
	 */
	public Long getTotalSize() {
		return this.totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
		if (getTotalPages() > 0 && this.pageNumber > getTotalPages()) {
			this.pageNumber = getTotalPages().intValue();
		}
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = (long) totalSize;
	}

	/**
	 * Dedicates the size of all records
	 */
	public Long getTotalPages() {
		if (totalSize < 0) {
			return 0L;
		}
		long count = totalSize / pageSize;
		if (totalSize % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * Dedicates the start position for fetch records from Database
	 */
	public Integer getStartPosition() {
		return (pageNumber - 1) * pageSize;
	}

	/**
	 * Dedicates the end position for fetch records from Database
	 */
	public Integer getEndPosition() {
		int count = pageNumber * pageSize;
		if (getTotalSize() > 0 && getTotalSize() < pageSize) {
			count = getTotalSize().intValue();
		}
		return count;
	}

	/**
	 * The first page number should be 1
	 */
	public Boolean getIsFirstPage() {
		return pageNumber == 1;
	}

	/**
	 * The last page number should be total pages size
	 */
	public Boolean getIsLastPage() {
		return pageNumber == getTotalPages().longValue();
	}

	/**
	 * Dedicates if it has previous page
	 */
	public Boolean getHasPreviousPage() {
		return pageNumber - 1 >= 1;
	}

	/**
	 * Dedicates if it has next page
	 */
	public Boolean getHasNextPage() {
		return pageNumber + 1 <= getTotalPages();
	}
	
	/**
	 * The action path is the current page URL.
	 * <p>URL is a relative path, like '/products'.</p>
	 */
	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	/**
	 * Return query string from filters
	 */
	public String getQueryStr() {
		return queryStr;
	}

	public boolean isPageable() {
		return pageNumber != null && pageSize != null;
	}
	
	public void setOrder(String order){
		this.pageOrder.setOrder(order);
	}
	
	public String getOrder(){
		return this.pageOrder.getOrderExpressions();
	}
	
	public Integer getPagesize() {
		return this.getPageSize();
	}

	public void setPagesize(Integer pagesize) {
		this.setPageSize(pagesize);
	}
	
	public Integer getPage(){
		return this.getPageNumber();
	}
	
	public void setPage(Integer page){
		this.setPageNumber(page);
	}
}
