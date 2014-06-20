package com.rlrg.dataserver.base.domain;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName="Counting")
public class CountableDTO {
	@JsonExport(name = "ControllerName")
	private String controllerName;

	@JsonExport(name = "Count")
	private Long count;

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
