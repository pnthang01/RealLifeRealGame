package com.rlrg.webserver.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.UserWebServiceReader;

@Service
public class UserService {

	@Autowired
	private UserWebServiceReader userReader;
	
	public UserDTO getUserByToken(String token) throws RestClientException, ConvertException{
		return userReader.getUserByToken(token);
	}
}
