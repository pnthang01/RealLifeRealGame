package com.rlrg.dataserver.utils.base.exception;

import com.rlrg.dataserver.utils.common.ExceptionCodeEnum;

public class RepositoryException extends BaseException{
	private static final long serialVersionUID = 1L;

	public RepositoryException() {
		super(ExceptionCodeEnum.DAO_ERROR);
	}

	public RepositoryException(String error) {
		super(ExceptionCodeEnum.DAO_ERROR, error);
	}

	public RepositoryException(Exception e) {
		super(ExceptionCodeEnum.DAO_ERROR, e.getMessage());
	}
}
