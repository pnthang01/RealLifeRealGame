package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class CategoryWebServiceReader extends BaseWebServiceReader<CategoryDTO> {
	
	private final String MODULE_NAME = "CategoryModule";
	
	private final String GET_ALL_CATEGORIES_BY_STATUS_URL = "category/getCategoriesByStatus?status={status}&pageNumber={pageNumber}";
	private final String GET_ALL_CATEGORIES_URL = "category/getAllCategories?pageNumber={pageNumber}";
	private final String GET_CATEGORY_BY_CODE_URL = "category/getCategoryByCode?code={code}";
	private final String SEARCH_CATEGORIES_BY_KEYWORD_URL = "category/searchCategories?keyword={keyword}&pageNumber={pageNumber}";
	private final String UPDATE_CATEGORY_URL = "category/update?restobject={restobject}";
	
	public ResultList<CategoryDTO> getCategoriesByStatus(boolean status, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_CATEGORIES_BY_STATUS_URL, MODULE_NAME, status, pageNumber);
	}
	
	public ResultList<CategoryDTO>  getAllCategories(Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_CATEGORIES_URL, MODULE_NAME, pageNumber);
	}
	
	public CategoryDTO getCategoryByCode(String code) throws RestClientException, ConvertException{
		return this.getAnObject(GET_CATEGORY_BY_CODE_URL, MODULE_NAME, code);
	}
	
	public ResultList<CategoryDTO> searchCategoriesByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(SEARCH_CATEGORIES_BY_KEYWORD_URL, MODULE_NAME, keyword, pageNumber);
	}
	
	public boolean updateCategory(CategoryDTO dto) throws RestClientException, ConvertException{
		return this.postAnObjectT(UPDATE_CATEGORY_URL, MODULE_NAME, dto);
	}

	@Override
	protected Class<CategoryDTO> getTClass() {
		return CategoryDTO.class;
	}

}
