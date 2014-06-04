package com.rlrg.dataserver.badge.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.badge.entity.Achievement;

@Repository
public interface AchievementRepository extends
		JpaRepository<Achievement, Long>, JpaSpecificationExecutor<Achievement> {

	@Query("SELECT a FROM Achievement a WHERE a.user.id = :userId")
	public List<Achievement> getAchievementByUser(@Param("userId") Long userId);

	@Query("SELECT a FROM Achievement a WHERE a.user.id = :userId AND a.badge.id = :badgeId")
	public List<Achievement> getAchievementByUserAndBadge(
			@Param("userId") Long userId, @Param("badgeId") Integer badgeId);

	@Query("SELECT a FROM Achievement a WHERE a.user.id = :userId AND a.date LIKE :dateYear")
	public List<Achievement> getAchievementByUserAndDate(
			@Param("userId") Long userId, @Param("dateYear") Integer dateYear);
}