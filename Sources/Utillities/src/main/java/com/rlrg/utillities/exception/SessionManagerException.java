package com.rlrg.utillities.exception;

public class SessionManagerException extends BaseException{
	private static final long serialVersionUID = 1L;

	public SessionManagerException() {
		super(ExceptionCodeEnum.SESSION_ID_EXISTS);
	}

	public SessionManagerException(String error) {
		super(ExceptionCodeEnum.SESSION_ID_EXISTS, error);
	}

	public SessionManagerException(Exception e) {
		super(ExceptionCodeEnum.SESSION_ID_EXISTS, e.getMessage());
	}
}
