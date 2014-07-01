package com.rlrg.dataserver.profile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.IRoleService;
import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.dataserver.profile.entity.Role;
import com.rlrg.dataserver.profile.repository.RoleRepository;
import com.rlrg.dataserver.utillities.Constants;

@Service
public class RoleService extends BaseService<Role, RoleDTO> implements IRoleService<Role, RoleDTO>{

	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepo;
	
	public List<RoleDTO> getAllRoleDTO(){
		return roleRepo.getAllRoleDTO();
	}

	public Role getRoleById(Integer roleId) {
		return roleRepo.findOne(roleId);
	}

	public Role getDefaultUserRole() {
		return roleRepo.getDefaultUserRole(Constants.DEFAULT_ROLE);
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
		return RoleDTO.class;
	}

}
