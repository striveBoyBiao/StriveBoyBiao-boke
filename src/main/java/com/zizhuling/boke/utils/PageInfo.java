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
	private List<T> pageDate;
	//查询的数量
	private Integer queryCount;
	private String type;
	private String state;
	private String mohu;
	//最新文章
	private List<T> newsDate;
	//排行榜
	private List<T> rankDate;
	//相关文档
	private List<T> relateDate;
	/**上一篇*/
	private T onDate;
	/**下一篇*/
	private T underDate;
	

	public PageInfo(String pageNo) {
		this.pageNo = pageNo;
		this.pageBegin = (Integer.parseInt(pageNo) - 1)*pageSize;
		
	}

	public T getOnDate() {
		return onDate;
	}

	public void setOnDate(T onDate) {
		this.onDate = onDate;
	}

	public T getUnderDate() {
		return underDate;
	}

	public void setUnderDate(T underDate) {
		this.underDate = underDate;
	}

	public List<T> getRelateDate() {
		return relateDate;
	}

	public void setRelateDate(List<T> relateDate) {
		this.relateDate = relateDate;
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

	public List<T> getPageDate() {
		return pageDate;
	}

	public void setPageDate(List<T> pageDate) {
		this.pageDate = pageDate;
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

	public List<T> getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(List<T> newsDate) {
		this.newsDate = newsDate;
	}

	public List<T> getRankDate() {
		return rankDate;
	}

	public void setRankDate(List<T> rankDate) {
		this.rankDate = rankDate;
	}
}
