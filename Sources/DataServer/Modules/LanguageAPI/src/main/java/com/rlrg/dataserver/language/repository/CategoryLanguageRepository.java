package com.rlrg.dataserver.language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.language.entity.CategoryLanguage;

@Repository
public interface CategoryLanguageRepository extends JpaRepository<CategoryLanguage, Integer>, JpaSpecificationExecutor<CategoryLanguage>{
	@Query("SELECT cl FROM CategoryLanguage cl WHERE cl.category = :categoryId AND cl.language.id = :languageId")
	public CategoryLanguage getCateLangByCateIdAndLangId(@Param("categoryId") Integer categoryId, @Param("languageId") Integer languageId);
}
