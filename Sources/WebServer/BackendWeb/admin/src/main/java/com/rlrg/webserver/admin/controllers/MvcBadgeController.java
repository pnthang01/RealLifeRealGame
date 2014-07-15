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

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.webserver.admin.form.SearchForm;
import com.rlrg.webserver.admin.service.MvcBadgeService;

@RequestMapping("/badge")
@Controller
public class MvcBadgeController {

	private static final Logger LOG = LoggerFactory.getLogger(MvcBadgeController.class);

	@Autowired
	private MvcBadgeService badgeService;

	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public String manage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			ModelMap model, @Valid SearchForm searchForm, BindingResult bResult) {
		try {
			ResultList<BadgeDTO> result = null;
			if (null == searchForm || null == searchForm.getKeyword()) {
				model.addAttribute("searchForm", new SearchForm());
				result = badgeService.getAllBadges(page);
			} else if (bResult.hasErrors()) {
				return "badge.manage";
			} else {
				result = badgeService.searchBadgesByKeyword(searchForm.getKeyword(), page);
				model.addAttribute("searchForm", searchForm);
			} 		
			//
			if (null != result) {
				model.addAttribute("totalPage",Math.ceil((double) result.getTotal()/ Constants.PAGE_SIZE));
				model.addAttribute("badges", result.getList());
			}
			return "badge.manage";
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

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public String edit(@RequestParam("id") Integer id, ModelMap model) {
		try {
			BadgeDTO data = badgeService.getBadgeById(id);
			model.addAttribute("badgeStatus", BadgeStatus.values());
			model.addAttribute("badgeDTO", data);
			//
			return "badge.edit";
		} catch (RestClientException | ConvertException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@Valid BadgeDTO data, BindingResult bResult) {
		try {
			if (bResult.hasErrors()) {
				return "badge.edit";
			} else {
				badgeService.updateBadge(data);
				return "redirect:/badge/manage.html";
			}
		} catch (RestClientException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (ConvertException e){
			LOG.error(e.getTechnicalMsg());
			return "error";
		}
	}

}
