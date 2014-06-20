package com.rlrg.dataserver.profile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.dataserver.profile.service.RoleService;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/role")
@Controller
public class RoleController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Get all roles in database
	 * @return
	 */
	@RequestMapping(value = "/getAllRole", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getAllRole(){
		String result = null;
		LOG.info("<< Starting webservice /permission/getPermissionByRole with parameters: none");
		try {
			List<RoleDTO> listDto = roleService.getAllRoleDTO();
			//
			result = roleService.encodeMutipleObjectsFromListV(listDto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = roleService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = roleService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /permission/getPermissionByRole");
		return result;
	}
	
}
