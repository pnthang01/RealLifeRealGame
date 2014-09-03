package com.rlrg.dataserver.task.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName="Category", pluralName="Categories")
public class CategoryDTO{
	@JsonExport(name="Code")
	private String code;

	@Length(min = 3, max = 30)
	@JsonExport(name="Name")	
	private String name;
	
	@NotBlank
	@JsonExport(name="Description")
	private String description;

	@Range(min = 1, max = 100)
	@JsonExport(name="Position")
	private Integer position;
	
	@JsonExport(name="Status")
	private Boolean status;
	
	@JsonExport(name="FileName")
	private String fileName;
	
	public CategoryDTO(){
	}
	
	public CategoryDTO(String code){
		this.code = code;
	}
	
	public CategoryDTO(String code, String name, String fileName){
		this.code = code;
		this.name = name;
		this.fileName = fileName;
	}
	
	public CategoryDTO(String code, String name, String description, Integer position, boolean status, String fileName){
		this.code = code;
		this.name = name;
		this.description = description;
		this.position = position;
		this.status = status;
		this.fileName = fileName;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder("Category:");
		str.append(" [Code:").append(code).append("]");
		str.append(",[Name:").append(name).append("]");
		str.append(",[Description:").append(description).append("]");
		str.append(",[Position:").append(position).append("]");
		str.append(",[Status:").append(status).append("]");
		return str.toString();
	}
	
}
