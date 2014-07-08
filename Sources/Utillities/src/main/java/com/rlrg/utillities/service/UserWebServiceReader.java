package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class UserWebServiceReader extends BaseWebServiceReader<UserDTO> {
	
	private final String MODULE_NAME = "UserModule";
	
	private final String GET_ALL_USERS_URL = "user/getUsers?pageNumber={pageNumber}";
	private final String UPDATE_USER_URL = "user/update?restobject={restobject}";
	
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
	public boolean updateCategory(UserDTO dto) throws RestClientException, ConvertException{
		return this.postAnObjectT(UPDATE_USER_URL, MODULE_NAME, dto);
	}

	@Override
	protected Class<UserDTO> getTClass() {
		return UserDTO.class;
	}

}