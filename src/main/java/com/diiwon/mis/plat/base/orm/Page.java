package com.diiwon.mis.plat.base.orm;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * <Strong>分页通用包装类</Strong><br>
 * 统一封装一个通用的面向表示层的综合对象，包含有查询对象的结果结合列表，以及该集合在对象所处的分页位置等等信息
 * 注意所有序号从1开始.
 * 	Page<User> page = new Page<User>;
	dao.findPage(page, "from User user");
	
 *
 */
@SuppressWarnings("serial")
public class Page<T> implements Serializable {
	
	// 公共变量 //
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	/**
	 * <strong>public</strong> 默认每页记录数
	 */
	public static int DEFAULT_PAGE_SIZE = 10;

	
	//分页参数 //
	/**
	 * <strong>public</strong> 当前页码数
	 */
	protected int currentPageNo = 1;	
	/**
	 * <strong>public</strong>  每页记录数
	 */
	protected int pageSize = DEFAULT_PAGE_SIZE; 	
	/**
	 * <strong>public</strong> 当前返回的分页集合对象List<T>
	 */
	protected List<T> result = Collections.emptyList();	
	/**
	 * <strong>public</strong> 总记录数。默认为-1（非自动统计）
	 */
	protected long totalCount = -1;
	/**
	 * <strong>public</strong> 是否自动统计总记录数
	 */	
	protected boolean autoCount = true;	
	/**
	 * <strong>public</strong> 该分页集合所使用的URL
	 */	
	protected String pageUrl = "errorPage.jsp";

	protected String formName ;
	
	protected int firstRows;
	
	protected int maxRows;

	//TODO 以下属性待验证是否需要	
	
	/**
	 * 该页从那一行开始
	 * 
	 */
	private long start; 
	protected String orderBy = null;
	protected String order = null;
	 
 
	
	public Page(final int pageSize) {
		setPageSize(pageSize);
	}

	public Page(final int Page, final int rows) {
		setCurrentPageNo(Page);
		setPageSize(rows);
	}
	
	/**
	 * 空分页记录对象构造函数
	 */
	public Page() {
		this(DEFAULT_PAGE_SIZE);
	}

	/**
	 *自定义分页对象构造函数
	 */
	public Page(long start, long totalSize, int pageSize, List<T>  data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.result=data;
	 
	}
	
	/**
	 * 获取总页数
	 */
	public long getTotalPageCount() {
		Assert.isTrue(pageSize > 0);		
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		}
		return totalCount / pageSize + 1;
	}
	
	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirstOfPage() {
		return ((currentPageNo - 1) * pageSize) + 1;
	}
 
	public int getLastOfPage() {
		return  currentPageNo   * pageSize ;
	}
 
	public static int getDEFAULT_PAGE_SIZE() {
		return DEFAULT_PAGE_SIZE;
	}

	public static void setDEFAULT_PAGE_SIZE(int dEFAULTPAGESIZE) {
		DEFAULT_PAGE_SIZE = dEFAULTPAGESIZE;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	/*public void setResult(List<Map<String, Object>> data) {
		this.result = (List<T>) data;
	}*/
	
	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

 

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public static String getAsc() {
		return ASC;
	}

	public static String getDesc() {
		return DESC;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public int getfirstRows() {
		firstRows = (getCurrentPageNo() - 1) * getPageSize();
		return firstRows;
	}

	public void setFirstRows(int firstRows) {
		this.firstRows = firstRows;
	}

	public int getMaxRows() {
		maxRows = getPageSize() * getCurrentPageNo();
		return maxRows;
	}

	public void setmaxRows(int maxRows) {
		this.maxRows = maxRows;
	}
	
	
}