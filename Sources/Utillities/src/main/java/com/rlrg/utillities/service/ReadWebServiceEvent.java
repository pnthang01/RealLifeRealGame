package com.rlrg.utillities.service;

public class ReadWebServiceEvent {
	private String url;
	private String json;
	private String moduleName;

	public ReadWebServiceEvent(String url, String json, String moduleName) {
		this.url = url;
		this.json = json;
		this.moduleName = moduleName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
