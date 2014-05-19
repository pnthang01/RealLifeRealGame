
package com.team.exeption;

import com.team.common.ExceptionCodeEnum;

public class DaoExeption extends VException {


	private static final long serialVersionUID = 1L;

	public DaoExeption() {
		super(ExceptionCodeEnum.DAO_ERROR);
	}

	public DaoExeption(String error) {
		super(ExceptionCodeEnum.DAO_ERROR, error);
	}

	public DaoExeption(Exception e) {
		super(ExceptionCodeEnum.DAO_ERROR, e.getMessage());
	}
}
