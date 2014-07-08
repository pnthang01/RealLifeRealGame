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

import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.webserver.admin.form.SearchForm;
import com.rlrg.webserver.admin.service.MvcLanguageService;

@RequestMapping("/language")
@Controller
public class MvcLanguageController {

	private static final Logger LOG = LoggerFactory.getLogger(MvcLanguageController.class);

	@Autowired
	private MvcLanguageService languageService;

	@RequestMapping(value = "/manage.html", method = RequestMethod.GET)
	public String manage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			ModelMap model, @Valid SearchForm searchForm, BindingResult bResult) {
		try {
			ResultList<LanguageDTO> result = null;
			if (null == searchForm || null == searchForm.getKeyword()) {
				model.addAttribute("searchForm", new SearchForm());
				result = languageService.getAllLanguages();
			} else if (bResult.hasErrors()) {
				return "language.manage";
			}
			//
			if (null != result) {
				model.addAttribute("totalPage",Math.ceil((double) result.getTotal()/ Constants.PAGE_SIZE));
				model.addAttribute("languages", result.getList());
			}
			return "language.manage";
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
