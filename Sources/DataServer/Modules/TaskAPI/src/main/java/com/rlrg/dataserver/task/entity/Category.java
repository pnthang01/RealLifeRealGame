package com.rlrg.dataserver.task.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category implements Serializable, Comparable<Category> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	@NotNull
//	@Size(min = 3, max = 45)
//	@Column(name = "name")
//	private String name;

	@NotNull
	@Column(name = "code")
	private String code;
//
//	@Size(min = 0, max = 500)
//	@Column(name = "description")
//	private String description;

	@Column(name = "position")
	private int position;

	@Column(name = "status")
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int compareTo(Category o) {
		if (null == o) {
			return -1;
		} else if (this.id == o.id && this.code.equalsIgnoreCase(o.code)) {
			return 0;
		} else {
			return -1;
		}

	}

}
