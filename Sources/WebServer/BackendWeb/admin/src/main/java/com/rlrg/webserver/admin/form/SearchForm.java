package com.rlrg.webserver.admin.form;

import org.hibernate.validator.constraints.Length;

public class SearchForm {
	
	@Length(min = 1, max = 30) 
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
