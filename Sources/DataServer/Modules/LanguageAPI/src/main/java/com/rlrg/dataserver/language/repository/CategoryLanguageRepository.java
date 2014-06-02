package com.rlrg.dataserver.language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.language.entity.CategoryLanguage;

@Repository
public interface CategoryLanguageRepository extends JpaRepository<CategoryLanguage, Integer>, JpaSpecificationExecutor<CategoryLanguage>{
	
}
