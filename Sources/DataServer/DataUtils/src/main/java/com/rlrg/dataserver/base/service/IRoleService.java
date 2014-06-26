package com.rlrg.dataserver.base.service;

import java.util.List;

public interface IRoleService<T, V> extends IBaseService<T, V>{
	public List<V> getAllRoleDTO();
	
	public T getRoleById(Integer roleId);
	
	public T getDefaultUserRole();

}
