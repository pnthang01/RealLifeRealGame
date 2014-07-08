package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.PermissionWebServiceReader;

@Service
public class MvcPermissionService {
	
	@Autowired
	private PermissionWebServiceReader permissionReader;

	public PermissionDTO getPermissionByRoleId(Integer roleId) throws RestClientException, ConvertException{
		return permissionReader.getPermissionByRoleId(roleId);
	}
}
