package com.rlrg.dataserver.task.service;

import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private JsonExporter jsonExporter;
	
	@Transactional
	public Category saveCategory(Category c){
		return cateRepo.save(c);
	}
	
	@Transactional
	public Category updateStatus(Integer id, boolean status){
		Category c = cateRepo.findOne(id);
		c.setStatus(status);
		//
		return cateRepo.saveAndFlush(c);
	}
	
	public Category getCategoryById(Integer id){
		return cateRepo.findOne(id);
	}
	
	public List<Category> getAllCategories(Integer pageNumber){
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return cateRepo.getAllCategories(pageRequest);
	}

	public List<Category> getCategoriesByStatus(boolean status, Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return cateRepo.getCategoriesByStatus(status, pageRequest);
	}

	@Override
	public CategoryDTO convertEntityToDTO(Category data) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCode(data.getCode());
//		dto.setDescription(data.getDescription());
//		dto.setName(data.getName());
		dto.setPosition(data.getPosition());
		dto.setStatus(data.getStatus());
		//
		return dto;
	}
	
}
