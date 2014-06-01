package com.rlrg.dataserver.task.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.task.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{
	
	@Query("SELECT c FROM Category c WHERE c.status = :status")
	public List<Category> getCategoriesByStatus(@Param("status") boolean status, Pageable pageRequest);
	
	@Query("SELECT c FROM Category c")
	public List<Category> getAllCategories(Pageable pageRequest);
}
