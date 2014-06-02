package com.rlrg.dataserver.task.service;

import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.language.service.CategoryLanguageService;
import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.dataserver.task.repository.CategoryRepository;
import com.rlrg.dataserver.utils.base.controller.WebVariables;
import com.rlrg.dataserver.utils.base.service.BaseService;
import com.rlrg.utillities.json.JsonExporter;

@Service
public class CategoryService extends BaseService<Category, CategoryDTO>{

	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	@Autowired
	private CategoryLanguageService langCateService;

	@Autowired
	private JsonExporter jsonExporter;
	
	@Transactional
	public Category saveCategory(Category c){
		return cateRepo.save(c);
	}
	
	@Transactional
	public Category updateStatus(String code, boolean status){
		Category c = cateRepo.getCategoryByCode(code);
		c.setStatus(status);
		//
		return cateRepo.saveAndFlush(c);
	}
	
	public Category update(CategoryDTO dto){
		Category c = cateRepo.getCategoryByCode(dto.getCode());
		c.setPosition(dto.getPosition());
		c.setStatus(dto.getStatus());
		return null;
	}
	
	/**
	 * Search a list of category base on keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	public List<CategoryDTO> searchCategoriesByKeyword(String keyword, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return cateRepo.searchCategoriesDTOByKeyword(keyword, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	/**
	 * Get all categories
	 * @param pageNumber
	 * @return
	 */
	public List<CategoryDTO> getAllCategories(Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return cateRepo.getAllCategoriesDTO(DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	/**
	 * Get a category bases on their code
	 * @param code
	 * @return
	 */
	public CategoryDTO getCategoryByCode(String code){
		return cateRepo.getCategoryDTOByCode(code, DEFAULT_LANGUAGE.getId());
	}

	/**
	 * Get categories by their status
	 * @param status
	 * @param pageNumber
	 * @return
	 */
	public List<CategoryDTO> getCategoriesByStatus(boolean status, Integer pageNumber) {
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return cateRepo.getCategoriesDTOByStatus(status, pageRequest);
	}

	@Override
	public CategoryDTO convertEntityToDTO(Category data) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCode(data.getCode());
		dto.setPosition(data.getPosition());
		dto.setStatus(data.getStatus());
		//
		return dto;
	}

	@Override
	public Category revertDTOToEntity(CategoryDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<CategoryDTO> getVClass() {
		return CategoryDTO.class;
	}
	
}
