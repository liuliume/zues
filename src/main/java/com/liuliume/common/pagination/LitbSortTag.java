package com.liuliume.common.pagination;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * LitbSortTag is the sort header tag for web server side sorting by click header.
 * <p>You should use it in the thead tag: </p>
 * <code>&lt;litb:sortableth order="lsin" seed="${seed}" label="LSIN" style="width:20%;"></code>
 * <p>After generated it should like: </p>
 * <code>&lt;th class="sorting" style="width:20%;" action="/inquiries/everyone?pagesize=10&amp;q=qq&amp;myTrackedProductsFlag=0&amp;myTrackedCategoriesFlag=1&amp;order=lsin==desc">LSIN&lt;/th></code>
 * <p></p>
 * @author limingxing
 *
 */
public class LitbSortTag extends AbstractHtmlElementTag {

	private static final long serialVersionUID = 2133473458294522234L;

	private String order;
	private String label;
	private Seed<?> seed;
	private String style = "";
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLabel() {
		return StringUtils.isNotBlank(label) ? label : StringUtils.EMPTY;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Seed<?> getSeed() {
		return seed;
	}

	public void setSeed(Seed<?> seed) {
		this.seed = seed;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		if(style != null && style != ""){
			this.style = (" style='" + style + "' ");
		}
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		JspWriter writer = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		String contextPath = request.getContextPath();
		try {
			StringBuffer sb = new StringBuffer();
			if (seed != null && seed.getPageOrder() != null) {
				PageOrder pageOrder = seed.getPageOrder();
				if (order.equalsIgnoreCase(pageOrder.getName())) {
					sb.append("<th class=\"sorting_" + (pageOrder.isAsc() ? "asc" : "desc") + "\""+style);
					sb.append(" action='"+ contextPath+"/" + seed.getActionPath() + "?"
							+ getQueryString(request, seed.getPageSize(), pageOrder, seed.getQueryStr())+"'>");
					sb.append(getLabel());
					sb.append("</th>");
				} else {
					sb.append("<th class='sorting'" + style);
					sb.append(" action='"+ contextPath+"/" + seed.getActionPath() + "?"
							+ getQueryString(request, seed.getPageSize(), pageOrder, seed.getQueryStr())+"'>");
					sb.append(getLabel());
					sb.append("</th>");
				}
				writer.print(sb.toString());
			} else {
				writer.print("<th" + style + ">" + getLabel() + "</th>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private String getQueryString(HttpServletRequest request, Integer pageSize, PageOrder pageOrder, String queryString) {
		String orderUrl = "";
		if (StringUtils.isNotBlank(queryString)) {
			if (queryString.indexOf("order=") != -1) {
				String[] queryStringArray = queryString.split("&");
				StringBuffer sb = new StringBuffer();
				if (queryStringArray != null && queryStringArray.length > 0) {
					for (int i = 0; i < queryStringArray.length; i++) {
						if (!queryStringArray[i].startsWith("order=")) {
							sb.append(queryStringArray[i] + "&");
						}
					}
				}
				sb.append("order="+ getOrder() + "==" + (pageOrder.isAsc() ? "desc" : "asc"));
				orderUrl = sb.toString();
			} else {
				orderUrl = queryString + "&order=" + getOrder() + "==" + (pageOrder.isAsc() ? "desc" : "asc");
			}
		} else {
			orderUrl = "&order="+ getOrder() + "==" + (pageOrder.isAsc() ? "desc" : "asc");
		}

		return ("pagesize=" + pageSize + orderUrl);
	}

}
