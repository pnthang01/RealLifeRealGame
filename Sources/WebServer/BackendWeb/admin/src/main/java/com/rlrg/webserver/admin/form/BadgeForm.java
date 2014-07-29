package com.rlrg.webserver.admin.form;

import org.springframework.web.multipart.MultipartFile;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;

public class BadgeForm {
	private Integer id;

	private String name;
	
	private MultipartFile file;

	private String description;

	private BadgeStatus status;

	private String eligibility;
	
	public static BadgeForm formData(BadgeDTO dto){
		BadgeForm form = new BadgeForm();
		form.setId(dto.getId());
		form.setName(dto.getName());
		form.setDescription(dto.getDescription());
		form.setStatus(dto.getStatus());
		form.setEligibility(dto.getEligibility());
		//
		return form;
	}
	
	public BadgeDTO getDTO(String fileName){
		BadgeDTO dto = new BadgeDTO();
		dto.setId(this.id);
		dto.setName(this.name);
		dto.setDescription(this.description);
		dto.setStatus(this.status);
		dto.setEligibility(this.eligibility);
		dto.setFileName(fileName);
		//
		return dto;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}
}
