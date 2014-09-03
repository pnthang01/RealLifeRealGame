package com.rlrg.webserver.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.UserWebServiceReader;
import com.rlrg.webserver.frontend.form.SignUpForm;

@Service
public class CommonService {
	
	@Autowired
	private UserWebServiceReader userReader;
	
	public UserDTO login(String username, String password) throws RestClientException, ConvertException{
		return userReader.login(username, password);
	}
	
	public void logout(String token) throws ConvertException{
		userReader.logout(token);
	}
	
	public boolean signup(SignUpForm form) throws ConvertException{
		UserDTO dto = new UserDTO();
		dto.setUsername(form.getUsername());
		dto.setFirstName(form.getFirstname());
		dto.setPassword(form.getPassword());
		dto.setEmail(form.getEmail());
		//
		return userReader.signup(dto);
	}
	
}
