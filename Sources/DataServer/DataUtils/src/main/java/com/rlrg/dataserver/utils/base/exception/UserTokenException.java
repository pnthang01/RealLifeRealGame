package com.rlrg.dataserver.utils.base.exception;

import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ExceptionCodeEnum;

public class UserTokenException extends BaseException{
	private static final long serialVersionUID = 1L;

	public UserTokenException() {
		super(ExceptionCodeEnum.TOKEN_NOT_EXISTS);
	}

	public UserTokenException(String error) {
		super(ExceptionCodeEnum.TOKEN_NOT_EXISTS, error);
	}

	public UserTokenException(Exception e) {
		super(ExceptionCodeEnum.TOKEN_NOT_EXISTS, e.getMessage());
	}
}
