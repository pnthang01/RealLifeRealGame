package com.rlrg.dataserver.task.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

	@Query("SELECT NEW com.rlrg.dataserver.task.dto.TaskDTO(" +
			"t.id, c.code, cl.cateName, t.name, t.description, t.createTime," +
			" t.completeTime, t.startTime, t.difficultyLevel, t.status, t.point"	+
			")"  +
			" FROM Task t INNER JOIN t.category c INNER JOIN c.cateLangs cl" +
			" WHERE t.name LIKE CONCAT('%', :name, '%') AND t.user.id = :userId AND cl.language.id = :languageId")
	public List<TaskDTO> getTasksByNameAndUser(@Param("name") String name, @Param("userId") Long userId, 
			@Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.TaskDTO(" +
			"t.id, c.code, cl.cateName, t.name, t.description, t.createTime," +
			" t.completeTime, t.startTime, t.difficultyLevel, t.status, t.point"	+
			")"  +
			" FROM Task t INNER JOIN t.category c INNER JOIN c.cateLangs cl" +
			" WHERE c.code = :categoryCode AND t.user.id = :userId AND cl.language.id = :languageId")
	public List<TaskDTO> getTasksByCategoryAndUser(@Param("categoryCode") String categoryCode, @Param("userId") Long userId
			, @Param("languageId") Integer languageId, Pageable pageable);
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.TaskDTO(" +
			"t.id, c.code, cl.cateName, t.name, t.description, t.createTime," +
			" t.completeTime, t.startTime, t.difficultyLevel, t.status, t.point"	+
			")"  +
			" FROM Task t INNER JOIN t.category c INNER JOIN c.cateLangs cl" +
			" WHERE t.id = :taskId AND cl.language.id = :languageId")
	public TaskDTO getTaskById(@Param("taskId") Long taskId, @Param("languageId") Integer languageId);
	
	@Query("SELECT t FROM Task t WHERE t.user.id = :userId")
	public Page<Task> getTasksByUser(@Param("userId") Integer userId, Pageable pageable);
	
	@Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.id = :taskId")
	public Task getTaskByIdAndUser(@Param("taskId") Long taskId, @Param("userId") Long userId);
	
	@Query("SELECT NEW com.rlrg.dataserver.task.dto.TaskDTO(" +
			"t.id, t.user.id, t.name, t.description)" + 
			" FROM Task t"+
			" WHERE " + 
			" ((t.id LIKE CONCAT('%', :keyword, '%'))" +
			" OR (t.user.id LIKE CONCAT('%', :keyword, '%'))" +
			" OR (t.name LIKE CONCAT('%', :keyword, '%')))")
	public List<TaskDTO> searchTasksDTOByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT COUNT(t.id) FROM Task t" +
			" WHERE " +
			" ((t.id LIKE CONCAT('%', :keyword, '%'))" +
			" OR (t.user.id LIKE CONCAT('%', :keyword, '%'))" +
			" OR (t.name LIKE CONCAT('%', :keyword, '%')))")
	public Long countTasksByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT COUNT(t.id) FROM Task t WHERE t.user.id = :userId")
	public Long countTotalCreatedTaskByUserId(@Param("userId") Long userId);
	
	@Query("SELECT COUNT(t.id) FROM Task t WHERE t.user.id = :userId AND t.category.tag = :cateTag" +
			" AND t.status = com.rlrg.dataserver.task.entity.enums.TaskStatus.COMPLETED")
	public Long countTotalCompletedTaskByUserIdAndCateTag(@Param("userId") Long userId, @Param("cateTag") String cateTag);
	
}
