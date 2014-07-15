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
	
	@Query("SELECT COUNT(b.id) FROM Badge b")
	public Long countAllBadges();
	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.BadgeDTO(" +
			"b.id, bl.name, bl.description, b.status, b.eligibility)" +
			" FROM Badge b INNER JOIN b.badgeLangs bl WHERE bl.language.id = :languageId AND b.id = :id")
	public BadgeDTO getBadgeDTOById(@Param("id") Integer id, @Param("languageId") Integer languageId);
	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.BadgeDTO(" +
			"b.id, bl.name, bl.description, b.status, b.eligibility" +
			")" +
		" FROM Badge b INNER JOIN b.badgeLangs bl WHERE b.status = :status AND bl.language.id = :languageId")
	public List<BadgeDTO> getBadgeDTOByStatus(@Param("status") BadgeStatus status, @Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.BadgeDTO(" +
			"b.id, bl.name, bl.description)" + 
			" FROM Badge b INNER JOIN b.badgeLangs bl"+
			" WHERE bl.language.id = :languageId AND" +
			" ((b.id LIKE CONCAT('%', :keyword, '%'))" +
			" OR (bl.description LIKE CONCAT('%', :keyword, '%'))" +
			" OR (bl.name LIKE CONCAT('%', :keyword, '%')))")
	public List<BadgeDTO> searchBadgesDTOByKeyword(@Param("keyword") String keyword, @Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT COUNT(b.id) FROM Badge b INNER JOIN b.badgeLangs bl" +
			" WHERE bl.language.id = :languageId AND" +
			" ((b.id LIKE CONCAT('%', :keyword, '%'))" +
			" OR (bl.description LIKE CONCAT('%', :keyword, '%'))" +
			" OR (bl.name LIKE CONCAT('%', :keyword, '%')))")
	public Long countBadgesByKeyword(@Param("keyword") String keyword, @Param("languageId") Integer languageId);
	
}
