package com.rlrg.dataserver.task.dto;

import java.util.Date;
import java.util.List;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;
import com.rlrg.utillities.annotation.JsonObject;

@JsonDTO(singularName = "Task", pluralName = "Tasks")
public class TaskDTO {

	@JsonExport(name = "ID")
	private Long id;

	@JsonObject
	private CategoryDTO category;

	@JsonExport(name = "Name")
	private String name;

	@JsonExport(name = "Description")
	private String description;

	@JsonExport(name = "Complete Time")
	private Date completeTime;

	@JsonExport(name = "Start Time")
	private Date startTime;

	@JsonExport(name = "Difficulty Level")
	private Integer difficultyLevel;

	@JsonExport(name = "Status")
	private Integer status;

	@JsonExport(name = "Point")
	private Integer point;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
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

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Task:");
		sb.append(" [ID = ").append(id).append("]");
		if(null != category){
			sb.append(" [Category = ").append(category.toString()).append("]");
		}
		sb.append(" [Name = ").append(name).append("]");
		sb.append(" [Complete Time = ").append(completeTime).append("]");
		sb.append(" [Point = ").append(point).append("]");
		sb.append(" [Status = ").append(status).append("]");
		//
		return sb.toString();
	}

	
}
