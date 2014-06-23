package com.rlrg.utillities.exception;

public class BadgeCheckerModuleException extends BaseException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadgeCheckerModuleException() {
		super(ExceptionCodeEnum.SYS_ERROR);
	}

	public BadgeCheckerModuleException(String error) {
		super(ExceptionCodeEnum.SYS_ERROR, error);
	}

	public BadgeCheckerModuleException(Exception e) {
		super(ExceptionCodeEnum.SYS_ERROR, e.getMessage());
	}
}
