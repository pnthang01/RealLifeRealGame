package com.rlrg.dataserver.language.entity;

// Generated May 22, 2014 3:25:47 PM by Hibernate Tools 3.6.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * LangCate generated by hbm2java
 */
@Entity
@Table(name = "category_language")
public class CategoryLanguage implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;

	@Column(name = "category_id", nullable = false)
	private Integer category;

	@Column(name = "name", length = 45)
	private String cateName;

	@Column(name = "description", length = 65535)
	private String description;

	public CategoryLanguage() {
	}

	public CategoryLanguage(Language language, Integer category) {
		this.language = language;
		this.category = category;
	}

	public CategoryLanguage(Language language, Integer category,
			String cateName, String description) {
		this.language = language;
		this.category = category;
		this.cateName = cateName;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
