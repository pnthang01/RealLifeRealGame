package com.rlrg.dataserver.badge.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Badge", pluralName = "Badges")
public class BadgeDTO {
	@JsonExport(name = "ID")
	private Integer id;

	@Length(min = 3, max = 30)
	@JsonExport(name = "Name")
	private String name;

	@NotBlank
	@JsonExport(name = "Description")
	private String description;

	@JsonExport(name = "Status")
	private BadgeStatus status;
	
	@JsonExport(name = "FileName")
	private String fileName;

	@NotBlank
	@JsonExport(name = "Eligibility")
	private String eligibility;

	public BadgeDTO() {
	}
	
	public BadgeDTO(Integer id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public BadgeDTO(Integer id, String name, String fileName, String description){
		this.id = id;
		this.name = name;
		this.description = description;
		this.fileName = fileName;
	}

	public BadgeDTO(Integer id, String name, String description,
			BadgeStatus status, String fileName, String eligibility) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.fileName = fileName;
		this.eligibility = eligibility;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BadgeStatus getStatus() {
		return status;
	}

	public void setStatus(BadgeStatus status) {
		this.status = status;
	}

}
