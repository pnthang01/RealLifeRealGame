package com.rlrg.dataserver.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.dataserver.task.service.CategoryService;
import com.rlrg.dataserver.utils.base.controller.BaseController;

@RequestMapping("/category")
@Controller
public class CategoryController extends BaseController {
	@Autowired
	private CategoryService cateService;
	
	/**
	 * Get Categories bases on their status
	 * @param taskId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCategoriesByStatus", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getCategoriesByStatus(@RequestParam("status") boolean status, @RequestParam("pageNumber") int pageNumber,
				HttpServletRequest request, HttpServletResponse response){
		List<Category> result = cateService.getCategoriesByStatus(status, pageNumber);
		//
		return cateService.encodeMutipleObjects(result);
	}
	
	/**
	 * Get all Categories
	 * @param pageNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAllCategories", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getAllCategories(@RequestParam("pageNumber") int pageNumber, HttpServletRequest request, HttpServletResponse response){
		List<Category> result = cateService.getAllCategories(pageNumber);
		//
		return cateService.encodeMutipleObjects(result);
	}
	
	/**
	 * Get Category By Id
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/getCategoryById", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getCategoryById(@RequestParam("id") Integer categoryId, HttpServletRequest request, HttpServletResponse response){
		Category result = cateService.getCategoryById(categoryId);
		//
		return cateService.encodeSingleObject(result);
	}
	
	/**
	 * Delete Category
	 * @param categoryId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateStatus", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String updateStatus(@RequestParam("id") Integer categoryId, @RequestParam("status") boolean status,
			HttpServletRequest request, HttpServletResponse response){
		Category result = cateService.updateStatus(categoryId, status);
		//
		return cateService.encodeSingleObject(result);
	}
}
