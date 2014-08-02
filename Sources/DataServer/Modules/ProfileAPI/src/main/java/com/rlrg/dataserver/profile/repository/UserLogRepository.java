package com.rlrg.dataserver.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.profile.entity.UserLog;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long>, JpaSpecificationExecutor<UserLog>{

	@Query("SELECT l FROM UserLog l WHERE l.userId = :userId")
	public List<UserLog> getUserLogByUserId(@Param("userId") Long userId);
	
	@Query("SELECT COUNT(l.id) FROM UserLog l WHERE l.userId = :userId AND l.action =:action")
	public Long countUserLogByUserIdAndAction(@Param("userId") Long userId, @Param("action") String action);

	@Query("SELECT COUNT(l.id) FROM UserLog l WHERE MONTH(l.time) = :month AND YEAR(l.time) = :year AND l.action =:action")
	public Long countLoginActionByTimeAndAction(@Param("month") Integer month, @Param("year") Integer year, @Param("action") String action);
}
