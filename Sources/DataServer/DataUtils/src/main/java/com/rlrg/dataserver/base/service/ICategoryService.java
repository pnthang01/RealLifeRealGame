package com.rlrg.dataserver.base.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.rlrg.dataserver.base.exception.RepositoryException;

public interface ICategoryService<T, V> extends IBaseService<T, V>{
	public T saveCategory(T c);
	
	@Transactional
	public void updateStatus(String code, boolean status) throws RepositoryException;
	
	@Transactional
	public void update(V dto) throws RepositoryException;

	public List<V> searchCategoriesByKeyword(String keyword, Integer pageNumber);

	public List<V> getAllCategories(Integer pageNumber);
	
	public Long countAllCategories();

	public T getCategoryByCode(String code);

	public V getCategoryDTOByCode(String code);

	public List<V> getCategoriesByStatus(boolean status, Integer pageNumber);
}
