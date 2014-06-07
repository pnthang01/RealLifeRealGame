package com.rlrg.utillities.exception;


public class ConvertException extends BaseException{
	private static final long serialVersionUID = 1L;

	public ConvertException() {
		super(ExceptionCodeEnum.SYS_ERROR);
	}

	public ConvertException(String error) {
		super(ExceptionCodeEnum.SYS_ERROR, error);
	}

	public ConvertException(Exception e) {
		super(ExceptionCodeEnum.SYS_ERROR, e.getMessage());
	}
}
