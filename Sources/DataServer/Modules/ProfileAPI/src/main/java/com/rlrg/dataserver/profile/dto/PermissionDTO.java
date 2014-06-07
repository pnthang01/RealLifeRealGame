package com.rlrg.dataserver.profile.dto;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Permission", pluralName = "Permissions")
public class PermissionDTO {

	@JsonExport(name = "ID")
	private Integer id;

	@JsonExport(name = "Role")
	private RoleDTO role;

	@JsonExport(name = "Regional")
	private String regional;
	
	public PermissionDTO(){
	}
	
	public PermissionDTO(Integer id, Integer roleId, String roleName, String regional){
		this.id = id;
		RoleDTO roleDTO = new RoleDTO(roleId, roleName);
		this.role = roleDTO;
		this.regional = regional;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

}
