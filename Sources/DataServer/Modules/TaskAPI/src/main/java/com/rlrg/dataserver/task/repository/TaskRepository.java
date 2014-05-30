package com.rlrg.dataserver.task.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

	@Query("SELECT t FROM Task t WHERE t.name LIKE :name AND t.user.id = :userId")
	public List<Task> getTasksByNameAndUser(@Param("name") String name, @Param("userId") Long userId);
	
	@Query("SELECT t FROM Task t WHERE t.category.id = :categoryId AND t.user.id = :userId")
	public List<Task> getTasksByCategoryAndUser(@Param("categoryId") Integer categoryId, @Param("userId") Long userId);
	
	@Query("SELECT t FROM Task t WHERE t.user.id = :userId")
	public Page<Task> getTasksByUser(@Param("userId") Integer userId, Pageable pageable);
}
