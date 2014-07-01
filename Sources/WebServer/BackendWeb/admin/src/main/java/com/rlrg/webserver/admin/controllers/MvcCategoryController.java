package com.rlrg.webserver.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.webserver.admin.form.SearchForm;
import com.rlrg.webserver.admin.service.MvcCategoryService;

@RequestMapping("/category")
@Controller
public class MvcCategoryController {

	@Autowired
	private MvcCategoryService categoryService;

	@RequestMapping("/manage")
	public String manage(@RequestParam(value="page", required=false, defaultValue="1") Integer page, ModelMap model){
		try {
			ResultList<CategoryDTO> result = categoryService.getAllCategories(page);
			
			model.addAttribute("totalPage", result.getTotal()/Constants.PAGE_SIZE);
			model.addAttribute("categories", result.getList());
			model.addAttribute("searchForm", new SearchForm());
			return "category.manage";
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConvertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
}
