package com.rlrg.utillities.service;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.utillities.exception.ConvertException;

public class CategoryWebServiceReader extends BaseWebServiceReader<CategoryDTO> {

	private final String GET_ALL_CATEGORIES_BY_STATUS_URL = "category/getCategoriesByStatus?status={status}&pageNumber={pageNumber}";
	private final String GET_ALL_CATEGORIES_URL = "category/getAllCategories?pageNumber={pageNumber}";
	private final String GET_CATEGORY_BY_CODE_URL = "category/getCategoryByCode?code={code}";
	private final String SEARCH_CATEGORIES_BY_KEYWORD_URL = "category/searchCategories?keyword={keyword}&pageNumber={pageNumber}";
	private final String UPDATE_CATEGORY_URL = "category/update?restobject={restobject}";
	
	public List<CategoryDTO> getCategoriesByStatus(boolean status, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_CATEGORIES_BY_STATUS_URL, status, pageNumber);
	}
	
	public List<CategoryDTO> getAllCategories(Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_CATEGORIES_URL, pageNumber);
	}
	
	public CategoryDTO getCategoryByCode(String code) throws RestClientException, ConvertException{
		return this.getAnObject(GET_CATEGORY_BY_CODE_URL, code);
	}
	
	public List<CategoryDTO> searchCategoriesByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(SEARCH_CATEGORIES_BY_KEYWORD_URL, keyword, pageNumber);
	}
	
	public boolean updateCategory(CategoryDTO dto) throws ConvertException{
		return this.postAnObjectT(UPDATE_CATEGORY_URL, dto);
	}

	@Override
	protected Class<CategoryDTO> getTClass() {
		return CategoryDTO.class;
	}

}
