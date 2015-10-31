package com.atguigu.bean;

import java.util.List;

public class Page<T> {
	// 页面数据，从数据库中查询
	private List<T> data;
	// 总记录数，从数据库中查询得出
	private int totalRecord;
	// 每页显示的条数，由用户指定
	private int pageSize;
	// 当前页,由前端页面传入
	private int pageNumber;
	// 总页数，计算得出
	private int totalPage;
	//页面的路径
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getIndex() {
		return (getPageNumber() - 1) * getPageSize();
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		if (pageNumber > this.getTotalPage()) {
			return this.getTotalPage();
		}
		if (pageNumber < 1) {
			return 1;
		}
		return pageNumber;

	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPage() {
		if(this.totalRecord%this.pageSize==0){
			this.totalPage = this.totalRecord/this.pageSize;
		}else{
			this.totalPage = this.totalRecord/this.pageSize+1;
		}
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "Page [data=" + data + ", totalRecord=" + totalRecord
				+ ", pageSize=" + pageSize + ", pageNumber=" + pageNumber
				+ ", totalPage=" + totalPage + "]";
	}

}
