package com.rlrg.dataserver.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.base.service.ICategoryService;
import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/category")
@Controller
public class CategoryController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ICategoryService<Category, CategoryDTO> categoryService;
	
	/**
	 * Get Categories bases on their status
	 * @param taskId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCategoriesByStatus", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getCategoriesByStatus(@RequestParam(value="status",required=true) boolean status, @RequestParam(value="pageNumber",required=false) int pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /category/getCategoriesByStatus with parameters: status={}, pageNumber={}", status, pageNumber);
		try {
			List<CategoryDTO> listDto = categoryService.getCategoriesByStatus(status, pageNumber);
			//
			result = categoryService.encodeMutipleObjectsFromListV(listDto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = categoryService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = categoryService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /category/getCategoriesByStatus");
		return result;
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
	public String getAllCategories(@RequestParam("pageNumber") int pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /category/getAllCategories with parameters: pageNumber={}", pageNumber);
		try {
			List<CategoryDTO> listDTO = categoryService.getAllCategories(pageNumber);
			//
			result = categoryService.encodeMutipleObjectsFromListV(listDTO);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = categoryService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = categoryService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /category/getAllCategories");
		return result;
	}
	
	/**
	 * Get Category By Id
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByCode", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getCategoryByCode(@RequestParam(value="code", required=true) String code){
		String result = null;
		LOG.info("<< Starting webservice /category/getCategoryByCode with parameters: code={}, pageNumber={}", code);
		try {
			CategoryDTO dto = categoryService.getCategoryDTOByCode(code);
			//
			result = categoryService.encodeSingleObjectFromVdto(dto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = categoryService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = categoryService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /category/getCategoryByCode");
		return result;
	}
	
	/**
	 * Delete Category
	 * @param categoryId
	 * @param request
	 * @param response
	 * @throws  
	 */
	@RequestMapping(value = "/updateStatus", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String updateStatusByCode(@RequestParam(value="code", required=true) String code, @RequestParam(value="status", required=true) boolean status,
			HttpServletRequest request, HttpServletResponse response){
		String result = null;
		LOG.info("<< Starting webservice /category/updateStatus with parameters: code={}, status={}", code, status);
		try {
			categoryService.updateStatus(code, status);
			RestObject restObject = RestObject.successBlank();
			result =  categoryService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = categoryService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = categoryService.encodeBlankRestObject(restObject);
		}
		//
		LOG.info("<< End webservice /category/updateStatus");
		return result;
	}
	
	/**
	 * Update a category by string of json
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/update", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String update(@RequestParam(value="restobject", required=true) String json){
		String result = null;
		LOG.info("<< Starting webservice /category/update with parameters: restobject={}", json);
		try {
			CategoryDTO dto = categoryService.decodeSingleObject(json);
			categoryService.update(dto);
			RestObject restObject = RestObject.successBlank();
			result =  categoryService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = categoryService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = categoryService.encodeBlankRestObject(restObject);
		}
		//
		LOG.info("<< End webservice /category/update");
		return result;
	}
	
	/**
	 * Search all categories which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchCategories", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String searchCategoriesByKeyword(@RequestParam(value="keyword", required=true) String keyword, 
			@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /category/searchCategories with parameters: keyword={}, pageNumber={}", keyword, pageNumber);
		try {
			List<CategoryDTO> listDTO = categoryService.searchCategoriesByKeyword(keyword, pageNumber);
			//
			result = categoryService.encodeMutipleObjectsFromListV(listDTO);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = categoryService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = categoryService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /category/searchCategories");
		return result;
	}
}
