package com.rlrg.dataserver.profile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.dataserver.profile.service.PermissionService;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/permission")
@Controller
public class PermissionController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * Get List of permission bases on roleId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/getPermissionByRole", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getPermissionByRole(@Param(value="roleId") Integer roleId){
		String result = null;
		LOG.info("<< Starting webservice /permission/getPermissionByRole with parameters: roleId={}", roleId);
		try {
			List<PermissionDTO> listDto = permissionService.getListOfPermissionDTOByRole(roleId);
			//
			result = permissionService.encodeMutipleObjectsFromListV(listDto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = permissionService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = permissionService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /permission/getPermissionByRole");
		return result;
	}
}
