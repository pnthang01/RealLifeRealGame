package com.rlrg.dataserver.profile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.dataserver.profile.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission>{
	
	@Query("SELECT NEW com.rlrg.dataserver.profile.dto.PermissionDTO(" +
			"p.id, r.id, r.name, p.regional" +
			")" +
			" FROM Permission p INNER JOIN p.role r WHERE r.id = :roleId")
	public List<PermissionDTO> getPermissionsByRole(@Param("roleId") Integer roleId);
}
