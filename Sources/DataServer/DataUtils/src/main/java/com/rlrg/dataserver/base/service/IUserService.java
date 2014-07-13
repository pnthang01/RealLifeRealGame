package com.rlrg.dataserver.base.service;

import java.util.List;

import com.rlrg.dataserver.base.exception.InvalidParamExeption;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.exception.UserTokenException;

public interface IUserService<T, V> extends IBaseService<T, V> {
	
	public String getUserPerformedString(Long userId);
	
	public T getUserByToken(String token) throws UserTokenException;

	public T getUserById(Long userId);

	public T getUserByUsername(String username);

	public void signup(V dto) throws Exception;

	public void updateProfile(V dto) throws Exception;

	public void updateUserRole(String token, Integer roleId) throws Exception;

	public V login(String username, String password) throws RepositoryException, InvalidParamExeption, Exception;

	public boolean updateUserPerformed(Long userId, String performed);

	public void updateUserPoint(String token, Integer point) throws Exception;

	public List<V> getAllUserDTO(Integer pageNumber);
	
	public List<V> searchUsersByKeyword(String keyword, Integer pageNumber);
	
	public Long countUsersByKeyword(String keyword);

}
