package com.volunteer.model;

import com.alibaba.fastjson.annotation.JSONField;

public class TableParameter {

	private int page = 1;
	private int current = 1;
	private int pageSize = 10;
	private int total = 0;
	private String sortField;
	private String sortType;

	public void setPage(int page) {
		this.page = page;
		this.current = page;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSONField(serialize = false)
	public int getStart() {
		return (page - 1) * pageSize;
	}

	@JSONField(serialize = false)
	public int getLength() {
		return this.pageSize;
	}

	public int getPage() {
		return page;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
