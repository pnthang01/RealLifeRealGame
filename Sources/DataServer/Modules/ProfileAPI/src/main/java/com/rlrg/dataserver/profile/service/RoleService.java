package com.rlrg.dataserver.profile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.dataserver.profile.entity.Role;
import com.rlrg.dataserver.profile.repository.RoleRepository;
import com.rlrg.dataserver.utils.base.controller.WebVariables;
import com.rlrg.dataserver.utils.base.service.BaseService;

@Service
public class RoleService extends BaseService<Role, RoleDTO> {

	private static final Logger LOG = LoggerFactory
			.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepo;

	public Role getRoleById(Integer roleId) {
		return roleRepo.findOne(roleId);
	}

	public Role getDefaultUserRole() {
		return roleRepo.getDefaultUserRole(WebVariables.DEFAULT_ROLE);
	}

	@Override
	public RoleDTO convertEntityToDTO(Role data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role revertDTOToEntity(RoleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<RoleDTO> getVClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
