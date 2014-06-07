package com.rlrg.dataserver.profile.dto;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Role", pluralName = "Roles")
public class RoleDTO {
	@JsonExport(name = "ID")
	private Integer id;

	@JsonExport(name = "Name")
	private String name;

	public RoleDTO() {
	}

	public RoleDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
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

}
