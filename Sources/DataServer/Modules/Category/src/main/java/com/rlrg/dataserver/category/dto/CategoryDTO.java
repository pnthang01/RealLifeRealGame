package com.rlrg.dataserver.category.dto;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName="Category", pluralName="Categories")
public class CategoryDTO {
	@JsonExport(name="Code")
	private String code;
	
	@JsonExport(name="Name")
	private String name;
	
	@JsonExport(name="Description")
	private String description;
	
	@JsonExport(name="Position")
	private Integer position;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder("Category:");
		str.append(" [Code:").append(code).append("]");
		str.append(",[Name:").append(name).append("]");
		str.append(",[Description:").append(description).append("]");
		str.append(",[Position:").append(position).append("]");
		return str.toString();
	}
	
	
}
