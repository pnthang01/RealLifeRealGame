package com.rlrg.dataserver.task.dto;

import java.util.Date;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.entity.enums.DifficultyLevel;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;
import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;
import com.rlrg.utillities.annotation.JsonObject;

@JsonDTO(singularName = "Task", pluralName = "Tasks")
public class TaskDTO {

	@JsonExport(name = "ID")
	private Long id;
	
	@JsonExport(name = "UserID")
	private Long userId;

	@JsonObject
	private CategoryDTO category;

	@JsonExport(name = "Name")
	private String name;

	@JsonExport(name = "Description")
	private String description;
	
	@JsonExport(name = "CreateTime")
	private Date createTime;

	@JsonExport(name = "CompleteTime")
	private Date completeTime;

	@JsonExport(name = "StartTime")
	private Date startTime;

	@JsonExport(name = "DifficultyLevel")
	private DifficultyLevel difficultyLevel;

	@JsonExport(name = "Status")
	private TaskStatus status;

	@JsonExport(name = "Point")
	private Integer point;
	
	public TaskDTO(){
	}
	
	public TaskDTO(Long id, Long userId, String name, String description){
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
	}
	
	public TaskDTO(Long id, String cateCode, String cateName, String name, String description, Date createTime,
			Date completeTime, Date startTime, DifficultyLevel diffcultyLevel,
			TaskStatus status, Integer point){
		this.id = id;
		CategoryDTO cate = new CategoryDTO(cateCode, cateName);
		this.category = cate;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.completeTime = completeTime;
		this.startTime = startTime;
		this.difficultyLevel = diffcultyLevel;
		this.status = status;
		this.point = point;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		sb.append(" [Status = ").append(status.name()).append("]");
		//
		return sb.toString();
	}

	
}
