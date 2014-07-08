package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.RoleWebServiceReader;

@Service
public class MvcRoleService {
	
	@Autowired
	private RoleWebServiceReader roleReader;

	public ResultList<RoleDTO> getAllRoles() throws RestClientException, ConvertException{
		return roleReader.getAllRoles();
	}
}
