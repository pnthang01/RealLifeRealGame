package com.rlrg.dataserver.language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.language.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language>{
	
	@Query("SELECT l FROM Language l WHERE l.i18n = :i18n")
	public Language getLanguageByI18N(@Param("i18n") String i18n);
}
