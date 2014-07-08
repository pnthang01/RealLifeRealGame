package com.rlrg.webserver.admin.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.webserver.admin.form.SearchForm;
import com.rlrg.webserver.admin.service.MvcPermissionService;

@RequestMapping("/permission")
@Controller
public class MvcPermissionController {

	private static final Logger LOG = LoggerFactory.getLogger(MvcPermissionController.class);

	@Autowired
	private MvcPermissionService permissionService;

	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public String manage(@RequestParam(value = "roleId", required = false, defaultValue = "1") Integer roleId,
			ModelMap model, @Valid SearchForm searchForm, BindingResult bResult) {
		try {
			PermissionDTO result = null;
			if (null == searchForm || null == searchForm.getKeyword()) {
				model.addAttribute("searchForm", new SearchForm());
				result = permissionService.getPermissionByRoleId(roleId);
			} else if (bResult.hasErrors()) {
				return "permission.manage";
			} else {
				result = permissionService.getPermissionByRoleId(Integer.parseInt(searchForm.getKeyword()));
				model.addAttribute("searchForm", searchForm);
			}
			//
			if (null != result) {
				model.addAttribute("permission", result.getRole());
			}
			return "permission.manage";
		} catch (RestClientException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (ConvertException e) {
			LOG.error(e.getTechnicalMsg());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}

}
