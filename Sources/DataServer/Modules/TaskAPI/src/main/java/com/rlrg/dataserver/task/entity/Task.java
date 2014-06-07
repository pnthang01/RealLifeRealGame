package com.rlrg.dataserver.task.entity;

//Generated May 22, 2014 3:25:47 PM by Hibernate Tools 3.6.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;

/**
 * Task generated by hbm2java
 */
@Entity
@Table(name = "task")
public class Task implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Column(name = "description", length = 65535)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "complete_time", length = 19)
	private Date completeTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", length = 19)
	private Date startTime;

	@Column(name = "difficulty_level")
	private Integer difficultyLevel;

	@Column(name = "status")
	private TaskStatus status;

	@Column(name = "point")
	private Integer point;

	public Task() {
	}

	public Task(User user, Category category, String name) {
		this.user = user;
		this.category = category;
		this.name = name;
	}

	public Task(User user, Category category, String name, String description,
			Date completeTime, Date startTime, Integer difficultyLevel,
			TaskStatus status, Integer point) {
		this.user = user;
		this.category = category;
		this.name = name;
		this.description = description;
		this.completeTime = completeTime;
		this.startTime = startTime;
		this.difficultyLevel = difficultyLevel;
		this.status = status;
		this.point = point;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public TaskStatus getStatus() {
		return this.status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
