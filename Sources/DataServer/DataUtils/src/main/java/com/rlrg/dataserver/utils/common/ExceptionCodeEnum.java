package com.rlrg.dataserver.utils.common;

public enum ExceptionCodeEnum {
	// Common error
	DAO_ERROR("dao.error"), BUSINESS_ERROR("business.error"), SERVICE_ERROR(
			"service.error"), SYS_ERROR("system.error"), OPERATION_NOT_SUPPORTED(
			"unsupportedOperation"), CONSTRAINS_INTEGRITY("dao.constraint"), USER_EXISTED(
			"user_existed"), USER_ERROR("user.notfound"), LOGIN_FAILED(
			"login.failed"), INVALID_PARAM("invalid.params"), ACCOUNT_IS_AVALIBLE(
			"account_is_available"), NOT_ACTIVEKEY("not_activekey"), INCORRECT_KEY(
			"incorret_key"), UPLOAD_FAIL("upload_FAIL"), ERROR_PARA(
			"param_error"), EMAIL_SEND_FALSE("Email send error"), TIME_OUT(
			"time_out"), TRANSACTION_ERROR("tran_error"), IMAGE_SHOW(
			"image_show"), ACCESS_DENIED("access.denied"), FIND_EX("find.exep"), PAY_MENT_EX(
			"payment.exep");

	private ExceptionCodeEnum(String errorCode) {
		this.errorCode = errorCode;
	}

	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}
}
