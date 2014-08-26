package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.UserWebServiceReader;

@Service
public class MvcUserService {
	
	@Autowired
	private UserWebServiceReader userReader;

	public ResultList<UserDTO> getAllUsers(Integer pageNumber) throws RestClientException, ConvertException{
		return userReader.getAllUsers(pageNumber);
	}
	
	public boolean updateUser(UserDTO dto) throws RestClientException, ConvertException{
		return userReader.updateUser(dto);
	}
	
	public ResultList<UserDTO> searchUsersByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return null;
		//return userReader.searchUsersByKeyword(keyword, pageNumber);
	}
}
