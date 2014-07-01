package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.CategoryWebServiceReader;

@Service
public class MvcCategoryService {
	
	@Autowired
	private CategoryWebServiceReader categoryReader;

	public ResultList<CategoryDTO>  getCategoriesByStatus(boolean status, Integer pageNumber) throws RestClientException, ConvertException{
		return categoryReader.getCategoriesByStatus(status, pageNumber);
	}
	
	public ResultList<CategoryDTO>  getAllCategories(Integer pageNumber) throws RestClientException, ConvertException{
		return categoryReader.getAllCategories(pageNumber);
	}
	
	public CategoryDTO getCategoryByCode(String code) throws RestClientException, ConvertException{
		return categoryReader.getCategoryByCode(code);
	}
	
	public ResultList<CategoryDTO>  searchCategoriesByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return categoryReader.searchCategoriesByKeyword(keyword, pageNumber);
	}
	
	public boolean updateCategory(CategoryDTO dto) throws RestClientException, ConvertException{
		return categoryReader.updateCategory(dto);
	}
}
