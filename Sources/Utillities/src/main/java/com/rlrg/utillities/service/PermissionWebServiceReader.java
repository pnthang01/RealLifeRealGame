package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.utillities.exception.ConvertException;

public class PermissionWebServiceReader extends BaseWebServiceReader<PermissionDTO> {
	
	private final String MODULE_NAME = "PermissionModule";
	
	private final String GET_PERMISSION_BY_ROLE_ID_URL = "permission/getPermissionByRoleId?roleId={roleId}";
	
	public PermissionDTO getPermissionByRoleId(Integer roleId) throws RestClientException, ConvertException{
		return this.getAnObject(GET_PERMISSION_BY_ROLE_ID_URL, MODULE_NAME, roleId);
	}

	@Override
	protected Class<PermissionDTO> getTClass() {
		return PermissionDTO.class;
	}

}
