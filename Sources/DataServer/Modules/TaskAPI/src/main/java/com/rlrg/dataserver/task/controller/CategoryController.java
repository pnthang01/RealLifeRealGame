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

import com.rlrg.dataserver.task.dto.CategoryDTO;
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
	public String getCategoriesByStatus(@RequestParam(value="status",required=true) boolean status, @RequestParam(value="pageNumber",required=true) int pageNumber,
				HttpServletRequest request, HttpServletResponse response){
		List<CategoryDTO> result = cateService.getCategoriesByStatus(status, pageNumber);
		//
		return cateService.encodeMutipleObjectsFromListV(result);
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
	public String getAllCategories(@RequestParam(value="pageNumber") int pageNumber, HttpServletRequest request, HttpServletResponse response){
		List<CategoryDTO> result = cateService.getAllCategories(pageNumber);
		//
		return cateService.encodeMutipleObjectsFromListV(result);
	}
	
	/**
	 * Get Category By Id
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByCode", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getCategoryByCode(@RequestParam(value="code", required=true) String code, HttpServletRequest request, HttpServletResponse response){
		CategoryDTO result = cateService.getCategoryByCode(code);
		//
		return cateService.encodeSingleObjectFromVdto(result);
	}
	
	/**
	 * Delete Category
	 * @param categoryId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateStatus", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String updateStatusByCode(@RequestParam(value="code", required=true) String code, @RequestParam(value="status", required=true) boolean status,
			HttpServletRequest request, HttpServletResponse response){
		Category result = cateService.updateStatus(code, status);
		//
		return cateService.encodeSingleObjectFromTdata(result);
	}
	
	/**
	 * Update a category by string of json
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/update", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String update(@RequestParam(value="restobject", required=true) String json){
		CategoryDTO dto = cateService.decodeSingleObject(json);//TODO
		Category c = cateService.update(dto);
		//
		return cateService.encodeSingleObjectFromTdata(c);
	}
	
	/**
	 * Search all categories which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchCategories", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String searchCategoriesByKeyword(@RequestParam(value="keyword", required=true) String keyword, @RequestParam(value="pageNumber") Integer pageNumber){
		List<CategoryDTO> listDTO = cateService.searchCategoriesByKeyword(keyword, pageNumber);
		//
		return cateService.encodeMutipleObjectsFromListV(listDTO);
	}
}
