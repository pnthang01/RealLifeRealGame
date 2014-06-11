package com.rlrg.dataserver.profile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.profile.dto.PermissionDTO;
import com.rlrg.dataserver.profile.entity.Permission;
import com.rlrg.dataserver.profile.repository.PermissionRepository;
import com.rlrg.dataserver.utils.base.service.BaseService;

@Service
public class PermissionService extends BaseService<Permission, PermissionDTO>{
	
	private static final Logger LOG = LoggerFactory.getLogger(PermissionService.class);
	
	@Autowired
	private PermissionRepository permissionRepo;
	
	/**
	 * Get all #Permission by roleId then convert it to list of #PermissionDTO
	 * @param roleId
	 * @return
	 */
	public List<PermissionDTO> getListOfPermissionDTOByRole(Integer roleId){
		return permissionRepo.getPermissionsByRole(roleId);
	}

	@Override
	public Permission revertDTOToEntity(PermissionDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<PermissionDTO> getVClass() {
		return PermissionDTO.class;
	}

	@Override
	public PermissionDTO convertEntityToDTO(Permission data) {
		// TODO Auto-generated method stub
		return null;
	}

}
