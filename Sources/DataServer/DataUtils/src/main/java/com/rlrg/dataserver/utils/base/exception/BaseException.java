package com.rlrg.dataserver.utils.base.exception;

import com.rlrg.dataserver.utils.common.ExceptionCodeEnum;

public class BaseException extends Exception{
	private static final long serialVersionUID = 1L;

	private String technicalMsg;
	private String errorCode;
	private Object[] errorParams;

	public BaseException(Exception ex) {
		super(ex);
	}

	/**
	 * Exception constructor with only error code
	 * 
	 * @param errorCode
	 *            Key for error string that will be looked up in client side
	 */
	public BaseException(ExceptionCodeEnum errorCode) {
		super(errorCode.getErrorCode());
		this.errorCode = errorCode.getErrorCode();
	}

	public BaseException(String errorMsg) {
		super(errorMsg);
	}

	/**
	 * Exception constructor with error code and error parameters Error string
	 * will be built in client side
	 */
	public BaseException(ExceptionCodeEnum errorCode, String[] params) {
		this(errorCode);
		this.errorParams = params.clone();
	}

	/**
	 * Exception constructor with error code and error message
	 */
	public BaseException(String errorCode, String technicalMsg) {
		super(errorCode);
		this.errorCode = errorCode;
		this.technicalMsg = technicalMsg;

	}

	/**
	 * Exception constructor with error code, error params and technical message
	 */
	public BaseException(ExceptionCodeEnum errorCode, String[] params,
			String technicalMsg) {
		this(errorCode, params);
		this.technicalMsg = technicalMsg;
	}

	/**
	 * Exception constructor with error code and detail message
	 */
	public BaseException(ExceptionCodeEnum errorCode, String technicalMsg) {
		this(errorCode);
		this.technicalMsg = technicalMsg;
	}

	public String getTechnicalMsg() {
		return technicalMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getErrorParams() {
		return errorParams.clone();
	}
}
