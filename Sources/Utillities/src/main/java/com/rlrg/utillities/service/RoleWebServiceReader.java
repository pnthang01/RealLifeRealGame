package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class RoleWebServiceReader extends BaseWebServiceReader<RoleDTO> {
	
	private final String MODULE_NAME = "RoleModule";
	
	private final String GET_ALL_ROLES = "role/getAllRoles";
	
	public ResultList<RoleDTO> getAllRoles() throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_ROLES, MODULE_NAME);
	}

	@Override
	protected Class<RoleDTO> getTClass() {
		return RoleDTO.class;
	}

}
