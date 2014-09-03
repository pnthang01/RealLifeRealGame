package com.rlrg.dataserver.badge.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.entity.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long>, JpaSpecificationExecutor<Achievement> {
	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.AchievementDTO(" +
				"a.id, b.id, bl.name, CONCAT(cfg.value, b.fileName), bl.description, a.achievedTime" +
				")" +
			" FROM Achievement a INNER JOIN a.badge b" +
			" INNER JOIN b.badgeLangs bl" +
			" INNER JOIN a.user u, Config cfg" +
			" WHERE u.username = :username AND bl.language.id = :languageId" +
			" AND cfg.key = com.rlrg.dataserver.utillities.Constants.STATIC_RESOURCES_URI_KEY")
	public List<AchievementDTO> getUserAchievementDTOs(@Param("username") String username, 
			@Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.badge.dto.AchievementDTO(" +
			"a.id, b.id, bl.name, CONCAT(cfg.value, b.fileName), bl.description, a.achievedTime" +
			")" +
		" FROM Achievement a INNER JOIN a.badge b" +
		" INNER JOIN b.badgeLangs bl" +
		" INNER JOIN a.user u, Config cfg" +
		" WHERE u.id = :userId AND bl.language.id = :languageId" +
		" AND cfg.key = com.rlrg.dataserver.utillities.Constants.STATIC_RESOURCES_URI_KEY")
	public List<AchievementDTO> getAchievementDTOs(@Param("userId") Long userId, 
			@Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT COUNT(a) FROM Achievement a WHERE a.user.id = :userId")
	public Long countAchievements(@Param("userId") Long userId);

	@Query("SELECT COUNT(a) FROM Achievement a WHERE a.badge.id = :badgeId")
	public Long countTimeBadgeBeAchieved(@Param("badgeId") Integer badgeId);

	@Query("SELECT a FROM Achievement a WHERE a.user.id = :userId")
	public List<Achievement> getAchievementByUser(@Param("userId") Long userId);

	@Query("SELECT a FROM Achievement a WHERE a.user.id = :userId AND a.badge.id = :badgeId")
	public List<Achievement> getAchievementByUserAndBadge(
			@Param("userId") Long userId, @Param("badgeId") Integer badgeId);

	@Query("SELECT a FROM Achievement a WHERE a.user.id = :userId AND a.achievedTime LIKE :dateYear")
	public List<Achievement> getAchievementByUserAndDate(
			@Param("userId") Long userId, @Param("dateYear") Integer dateYear);
	
	@Query("SELECT a.badge.id FROM Achievement a WHERE a.user.id = :userId")
	public List<Integer> getAllBadgeIdByUserId(@Param("userId") Long userId);

	
}
