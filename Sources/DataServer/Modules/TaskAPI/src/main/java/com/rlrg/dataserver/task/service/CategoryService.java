package com.rlrg.dataserver.task.service;

import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.ICategoryLanguageService;
import com.rlrg.dataserver.base.service.ICategoryService;
import com.rlrg.dataserver.language.dto.CateLangDTO;
import com.rlrg.dataserver.language.entity.CategoryLanguage;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.service.ConfigService;
import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.dataserver.task.repository.CategoryRepository;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.utillities.json.JsonExporter;

@Service
public class CategoryService extends BaseService<Category, CategoryDTO> implements ICategoryService<Category, CategoryDTO>{

	@Transient
	private final static Logger LOG = LoggerFactory.getLogger(CategoryService.class);

	@Autowired 
	private ConfigService configService;
	
	@Autowired
	private CategoryRepository cateRepo;

	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	@Autowired
	private ICategoryLanguageService<CategoryLanguage, CateLangDTO> cateLangService;

	@Autowired
	private JsonExporter jsonExporter;

	public Category saveCategory(Category c){
		return cateRepo.save(c);
	}
	
	public String getNewCode(){
		String newCode = RandomStringUtils.random(8, true, true);
		while(cateRepo.exitsCategoryCode(newCode)){
			newCode = RandomStringUtils.random(8, true, true);
		}
		//
		return newCode;
	}
	
	@Transactional
	public void updateStatus(String code, boolean status) throws RepositoryException{
		try {
			Category c = cateRepo.getCategoryByCode(code);
			c.setStatus(status);
			cateRepo.save(c);
			//
		} catch(Exception e){
			LOG.error("<< Error occurs when updating category'status with code: " + code, e);
			throw new RepositoryException("Error occurs when updating category.");
		}
	}
	
	@Transactional
	public void update(CategoryDTO dto) throws RepositoryException{
		try {
			Category c = cateRepo.getCategoryByCode(dto.getCode());
			if(null == c){
				LOG.error("Cannot find entity Category with code:{}.", dto.getCode());
				throw new RepositoryException("Cannot find entity");
			}
			//
			CategoryLanguage cateLang = cateLangService.getCateLangByCateIdAndLangId(c.getId(), DEFAULT_LANGUAGE.getId());
			if(null == cateLang){
				LOG.error("Cannot find entity CategoryLanguage with code:{}.", dto.getCode());
				throw new RepositoryException("Cannot find entity");
			}
			cateLang.setCateName(dto.getName());
			cateLang.setDescription(dto.getDescription());
			cateLangService.save(cateLang);
			//
			c.setPosition(dto.getPosition());
			c.setStatus(dto.getStatus());
			cateRepo.save(c);
			//
		} catch(Exception e){
			LOG.error("<< Error occurs when updating category'status with code: " + dto.getCode(), e);
			throw new RepositoryException("Error occurs when updating category.");
		}
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
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Integer.valueOf(configService.getConfig(Constants.PAGE_SIZE_KEY)));
		//
		return cateRepo.searchCategoriesDTOByKeyword(keyword, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	

	@Override
	public Long countCategoriesByKeyword(String keyword) {
		return cateRepo.countCategoriesByKeyword(keyword, DEFAULT_LANGUAGE.getId());
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
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Integer.valueOf(configService.getConfig(Constants.PAGE_SIZE_KEY)));
		//
		return cateRepo.getAllCategoriesDTO(DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	@Override
	public Long countAllCategories(){
		return cateRepo.countAllCategories();
	}
	
	/**
	 * Get #Category bases on their code
	 * @param code
	 * @return
	 */
	public Category getCategoryByCode(String code){
		return cateRepo.getCategoryByCode(code);
	}
	
	/**
	 * Get a #CategoryDTO bases on their code
	 * @param code
	 * @return
	 */
	public CategoryDTO getCategoryDTOByCode(String code){
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
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Integer.valueOf(configService.getConfig(Constants.PAGE_SIZE_KEY)));
		//
		List<CategoryDTO> list = cateRepo.getCategoriesDTOByStatus(status, DEFAULT_LANGUAGE.getId(), pageRequest);
		formingResourceUri(list);
		return list;
	}
	
	private void formingResourceUri(CategoryDTO dto){
		StringBuffer str = new StringBuffer(configService.getConfig(Constants.STATIC_RESOURCES_URI_KEY));
		str.append(dto.getFileName());
		dto.setFileName(str.toString());
	}
	
	private void formingResourceUri(List<CategoryDTO> list){
		for(int i = 0 ; i < list.size() ; i++){
			formingResourceUri(list.get(i));
		}
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
