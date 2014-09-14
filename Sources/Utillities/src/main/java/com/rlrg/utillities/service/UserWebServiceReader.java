package com.rlrg.utillities.service;

import java.io.UnsupportedEncodingException;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ConvertException;

public class UserWebServiceReader extends BaseWebServiceReader<UserDTO> {
	
	private final String MODULE_NAME = "UserModule";
	
	private final String GET_ALL_USERS_URL = "user/getUsers?pageNumber={pageNumber}";
	private final String UPDATE_USER_URL = "user/update?restobject={restobject}";
	private final String LOGIN_URL = "user/login?username={username}&password={password}";
	private final String LOGOUT_URL = "user/logout?token={token}";
	private final String SIGNUP_URL = "user/signup?restobject={restobject}";
	private final String GET_USET_BY_TOKEN_URL = "user/getUserByToken?token={token}";
	
	public UserDTO getUserByToken(String token) throws RestClientException, ConvertException{
		return this.getAnObject(GET_USET_BY_TOKEN_URL, MODULE_NAME, token);
	}
	
	public void logout(String token) throws RestClientException, ConvertException{
		this.getForVoid(LOGOUT_URL, MODULE_NAME, token);
	}
	
	public ResultList<UserDTO> getAllUsers(Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_USERS_URL, MODULE_NAME, pageNumber);
	}
	
	/**
	 * Use to create a new user or update a current user.
	 * @param dto
	 * @return
	 * @throws RestClientException
	 * @throws ConvertException
	 */
	public boolean updateUser(UserDTO dto) throws RestClientException, ConvertException{
		return this.postAnObjectT(UPDATE_USER_URL, MODULE_NAME, dto);
	}
	
	/**
	 * Login method
	 * @param username
	 * @param password
	 * @return
	 * @throws RestClientException
	 * @throws UnsupportedEncodingException 
	 * @throws BaseException 
	 */
	public UserDTO login(String username, String password) throws RestClientException, UnsupportedEncodingException, BaseException {
		return this.postAnObject(LOGIN_URL, MODULE_NAME, username, password);
	}
	
	public String signup(UserDTO dto) throws ConvertException{
		return this.postAnObjectTForMessage(SIGNUP_URL, MODULE_NAME, dto);
	}

	@Override
	protected Class<UserDTO> getTClass() {
		return UserDTO.class;
	}

}
