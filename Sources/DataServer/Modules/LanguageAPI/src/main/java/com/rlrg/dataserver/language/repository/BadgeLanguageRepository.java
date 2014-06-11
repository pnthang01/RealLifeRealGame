package com.rlrg.dataserver.language.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.language.entity.BadgeLanguage;

@Repository
public interface BadgeLanguageRepository  extends JpaRepository<BadgeLanguage, Integer>, JpaSpecificationExecutor<BadgeLanguage>{
	
	@Query("SELECT bl FROM BadgeLanguage bl WHERE bl.badge = :badgeId AND bl.language.id = :languageId")
	public BadgeLanguage getBadgeLangByBadgeIdAndLangId(@Param("badgeId") Integer badgeId, @Param("languageId") Integer languageId);
}
