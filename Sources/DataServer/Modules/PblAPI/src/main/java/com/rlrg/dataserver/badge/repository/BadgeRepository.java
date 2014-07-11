package com.rlrg.dataserver.badge.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer>, JpaSpecificationExecutor<Badge>{

	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.BadgeDTO(" +
				"b.id, bl.name, bl.description, b.status, b.eligibility" +
				")" +
			" FROM Badge b INNER JOIN b.badgeLangs bl WHERE bl.language.id = :languageId")
	public List<BadgeDTO> getAllBadgeDTO(@Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.BadgeDTO(" +
			"b.id, bl.name, bl.description, b.status, b.eligibility" +
			")" +
		" FROM Badge b INNER JOIN b.badgeLangs bl WHERE b.status = :status AND bl.language.id = :languageId")
	public List<BadgeDTO> getBadgeDTOByStatus(@Param("status") BadgeStatus status, @Param("languageId") Integer languageId, Pageable pageable);
	
}
