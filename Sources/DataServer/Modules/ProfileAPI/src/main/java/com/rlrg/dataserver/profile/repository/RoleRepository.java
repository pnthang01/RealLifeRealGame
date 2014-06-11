package com.rlrg.dataserver.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.dataserver.profile.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role>{
	
	@Query("SELECT NEW com.rlrg.dataserver.profile.dto.RoleDTO(" +
				"r.id, r.name" +
				")" +
			" FROM Role r")
	public List<RoleDTO> getAllRoleDTO();
	
	@Query("SELECT r FROM Role r WHERE r.id = :roleId")
	public Role getDefaultUserRole(@Param("roleId") Integer roleId);
}
