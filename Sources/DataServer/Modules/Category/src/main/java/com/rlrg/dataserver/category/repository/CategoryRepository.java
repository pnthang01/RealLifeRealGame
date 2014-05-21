package com.rlrg.dataserver.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{
	
	@Query("Select c FROM Category WHERE c.status = :status")
	public List<Category> getAllCategoryByStatus(boolean status);
}
