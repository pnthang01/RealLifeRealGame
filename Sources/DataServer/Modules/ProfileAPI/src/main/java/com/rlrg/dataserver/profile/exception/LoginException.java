package com.rlrg.dataserver.profile.exception;

import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ExceptionCodeEnum;

/**
 * 
 * @author Anh Dinh
 */
public class LoginException extends BaseException {
	
	public LoginException() {
		super(ExceptionCodeEnum.LOGIN_FAILED);
	}

	public LoginException(String msg) {
		super(ExceptionCodeEnum.LOGIN_FAILED, msg);
	}
}
