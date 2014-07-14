package com.rlrg.dataserver.profile.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	@Query("SELECT u FROM entity.User u WHERE u.username = :username AND u.password = :password")
	public User login(@Param("username") String username, @Param("password") String password);
	
	@Query("SELECT u FROM entity.User u WHERE u.username = :username ")
	public User findUserByUsername(@Param("username") String username);
	
	@Query("SELECT u.performed FROM entity.User u WHERE u.id = :userId")
	public String getUserPerformedString(@Param("userId") Long userId);
	
	@Query("SELECT NEW com.rlrg.dataserver.profile.dto.UserDTO(" +
			"u.username, u.firstName, u.email, u.lastLogin, u.gender" +
			")" +
			" FROM entity.User u")
	public List<UserDTO> getAllUserDTO(Pageable pagealbe);
	
	@Query("SELECT NEW com.rlrg.dataserver.profile.dto.UserDTO(" +
			"u.username, u.firstName, u.email)" + 
			" FROM User u"+
			" WHERE " +
			" ((u.username LIKE CONCAT('%', :keyword, '%'))" +
			" OR (u.firstName LIKE CONCAT('%', :keyword, '%'))" +
			" OR (u.email LIKE CONCAT('%', :keyword, '%')))")
	public List<UserDTO> searchUsersDTOByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT COUNT(u.username) FROM User u" +
			" WHERE " +
			" ((u.username LIKE CONCAT('%', :keyword, '%'))" +
			" OR (u.firstName LIKE CONCAT('%', :keyword, '%'))" +
			" OR (u.email LIKE CONCAT('%', :keyword, '%')))")
	public Long countUsersByKeyword(@Param("keyword") String keyword);
	
}
