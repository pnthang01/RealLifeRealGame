package com.rlrg.dataserver.task.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.base.dto.CategoryDTO;
import com.rlrg.dataserver.task.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.CategoryDTO(" +
			"c.code, cl.cateName, cl.description, c.position, c.status)" + 
			" FROM Category c"+
			" LEFT OUTER JOIN c.cateLangs cl" +
			" WHERE cl.language.id = :languageId" + 
			" AND c.code = :code")
	public CategoryDTO getCategoryDTOByCode(@Param("code") String code, @Param("languageId") Integer languageId);
	
	@Query("SELECT c FROM Category c WHERE c.code = :code")
	public Category getCategoryByCode(@Param("code") String code);
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.CategoryDTO(" +
			"c.code, cl.cateName, cl.description, c.position, c.status)" + 
			" FROM Category c"+
			" LEFT OUTER JOIN c.cateLangs cl" +
			" WHERE cl.language.id = :languageId" + 
			" AND c.status = :status")
	public List<CategoryDTO> getCategoriesDTOByStatus(@Param("status") boolean status, @Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.CategoryDTO(" +
			"c.code, cl.cateName, cl.description, c.position, c.status)" + 
			" FROM Category c"+
			" LEFT OUTER JOIN c.cateLangs cl" +
			" WHERE cl.language.id = :languageId" +
			" AND " +
			" ((cl.cateName LIKE CONCAT('%', :keyword, '%'))" +
			" OR (cl.description LIKE CONCAT('%', :keyword, '%'))" +
			" OR (c.code LIKE CONCAT('%', :keyword, '%')))")
	public List<CategoryDTO> searchCategoriesDTOByKeyword(@Param("keyword") String keyword, @Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.CategoryDTO(" +
			"c.code, cl.cateName, cl.description, c.position, c.status)" + 
			" FROM Category c"+
			" LEFT OUTER JOIN c.cateLangs cl" +
			" WHERE cl.language.id = :languageId")
	public List<CategoryDTO> getAllCategoriesDTO(@Param("languageId") Integer languageId, Pageable pageable);
}
