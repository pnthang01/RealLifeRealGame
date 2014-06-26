package com.rlrg.dataserver.base.service;

import java.util.List;

public interface IPermissionService<T, V> extends IBaseService<T, V> {
	public List<V> getListOfPermissionDTOByRole(Integer roleId);

}
