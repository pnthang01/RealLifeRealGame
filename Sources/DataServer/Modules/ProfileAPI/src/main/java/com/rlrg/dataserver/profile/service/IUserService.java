package com.rlrg.dataserver.profile.service;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.exception.LoginException;
import com.rlrg.dataserver.profile.form.LoginForm;
import com.rlrg.dataserver.utils.base.exception.InvalidParamExeption;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;

public interface IUserService {
	public UserDTO login(LoginForm loginForm) throws RepositoryException, LoginException, InvalidParamExeption;
}
