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
	
	@Query("SELECT u FROM entity.User u WHERE u.username = :username AND u.token = :token")
	public User getUserByUsernameAndToken(@Param("username") String username, @Param("token") String token);
	
	@Query("SELECT NEW com.rlrg.dataserver.profile.dto.UserDTO(" +
			"u.username, u.firstName, u.email, u.lastLogin, u.gender" +
			")" +
			" FROM entity.User u")
	public List<UserDTO> getAllUserDTO(Pageable pagealbe);
}
