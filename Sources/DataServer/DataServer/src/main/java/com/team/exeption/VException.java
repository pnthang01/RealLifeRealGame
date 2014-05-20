/******************************************************************************/
/* .......................................................................... */
/* ....\\........//.||........||..//====\\..||====\\..||======.\\........//.. */
/* .....\\......//..||\\....//||.||......||.||.....||.||........\\......//... */
/* ......\\....//...||.\\..//.||.||......||.||.....||.||======...\\....//.... */
/* .......\\..//....||..\\//..||.||......||.||.....||.||..........\\..//..... */
/* ........\\//.....||........||..\\====//..||====//..||======.....\\//...... */
/* .........................VMODEV.@2013..................................... */
/******************************************************************************/
package com.team.exeption;

import com.team.common.ExceptionCodeEnum;

/**
 * @author Trong 24-11-2013 - 01:14:44
 */
public abstract class VException extends Exception {
	private static final long serialVersionUID = 1L;

	private String technicalMsg;
	private String errorCode;
	private Object[] errorParams;

	public VException(Exception ex) {
		super(ex);
	}

	/**
	 * Exception constructor with only error code
	 * 
	 * @param errorCode
	 *            Key for error string that will be looked up in client side
	 */
	public VException(ExceptionCodeEnum errorCode) {
		super(errorCode.getErrorCode());
		this.errorCode = errorCode.getErrorCode();
	}

	public VException(String errorMsg) {
		super(errorMsg);
	}

	/**
	 * Exception constructor with error code and error parameters Error string
	 * will be built in client side
	 */
	public VException(ExceptionCodeEnum errorCode, String[] params) {
		this(errorCode);
		this.errorParams = params.clone();
	}

	/**
	 * Exception constructor with error code and error message
	 */
	public VException(String errorCode, String technicalMsg) {
		super(errorCode);
		this.errorCode = errorCode;
		this.technicalMsg = technicalMsg;

	}

	/**
	 * Exception constructor with error code, error params and technical message
	 */
	public VException(ExceptionCodeEnum errorCode, String[] params,
			String technicalMsg) {
		this(errorCode, params);
		this.technicalMsg = technicalMsg;
	}

	/**
	 * Exception constructor with error code and detail message
	 */
	public VException(ExceptionCodeEnum errorCode, String technicalMsg) {
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
