package com.zizhuling.boke.utils;
import java.util.List;

/**
 * 分页
 * 
 * @author igeek
 *
 */
public class PageInfo<T> {
	//当前页
	private String pageNo;
	//每页大小
	public static int pageSize =10;
	//每页开始
	public static int pageBegin=0;
	//总共多少条数据
	private int rowCount;
	//页的数量
	private int pageCount;
	//每页数据
	private List<T> pageData;
	//查询的数量
	private Integer queryCount;
	private String type;
	private String state;
	private String mohu;
	//最新文章
	private List<T> newsData;
	//排行榜
	private List<T> rankData;
	//相关文档
	private List<T> relateData;
	/**上一篇*/
	private T onData;
	/**下一篇*/
	private T underData;
	

	public PageInfo(String pageNo) {
		this.pageNo = pageNo;
		this.pageBegin = (Integer.parseInt(pageNo) - 1)*pageSize;
		
	}

	public T getOnData() {
		return onData;
	}

	public void setOnData(T onData) {
		this.onData = onData;
	}

	public T getUnderData() {
		return underData;
	}

	public void setUnderData(T underData) {
		this.underData = underData;
	}

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getMohu() {
		return mohu;
	}


	public void setMohu(String mohu) {
		this.mohu = mohu;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public Integer getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}

	public List<T> getNewsData() {
		return newsData;
	}

	public void setNewsData(List<T> newsData) {
		this.newsData = newsData;
	}

	public List<T> getRankData() {
		return rankData;
	}

	public void setRankData(List<T> rankData) {
		this.rankData = rankData;
	}

	public List<T> getRelateData() {
		return relateData;
	}

	public void setRelateData(List<T> relateData) {
		this.relateData = relateData;
	}
}
