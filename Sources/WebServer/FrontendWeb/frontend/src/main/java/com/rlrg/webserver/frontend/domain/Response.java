package com.rlrg.webserver.frontend.domain;

public class Response {
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	private String status = null;
	private Object result = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
