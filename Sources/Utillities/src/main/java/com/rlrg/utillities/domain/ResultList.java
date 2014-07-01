package com.rlrg.utillities.domain;

import java.util.List;

public class ResultList<T> {
	private Long total;
	private List<T> list;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
