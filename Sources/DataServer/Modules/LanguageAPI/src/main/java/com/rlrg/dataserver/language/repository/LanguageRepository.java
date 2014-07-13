package com.rlrg.dataserver.language.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.dataserver.language.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language>{
	
	@Query("SELECT l FROM Language l WHERE l.i18n = :i18n")
	public Language getLanguageByI18N(@Param("i18n") String i18n);
	
	@Query("SELECT l FROM Language l")
	public List<Language> getAllLanguages();
	
	@Query("SELECT NEW com.rlrg.dataserver.language.dto.LanguageDTO(" +
			"l.id, l.language, l.country, l.i18n)" + 
			" FROM Language l"+
			" WHERE ((l.language LIKE CONCAT('%', :keyword, '%'))" +
			" OR (l.country LIKE CONCAT('%', :keyword, '%'))" +
			" OR (l.i18n LIKE CONCAT('%', :keyword, '%')))")
	public List<LanguageDTO> searchLanguagesDTOByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT COUNT(l.id) FROM Language l" +
			" WHERE ((l.language LIKE CONCAT('%', :keyword, '%'))" +
			" OR (l.country LIKE CONCAT('%', :keyword, '%'))" +
			" OR (l.i18n LIKE CONCAT('%', :keyword, '%')))")
	public Long countLanguagesByKeyword(@Param("keyword") String keyword);
}
