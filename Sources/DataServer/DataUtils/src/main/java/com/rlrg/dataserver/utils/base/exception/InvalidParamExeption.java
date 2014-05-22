package com.rlrg.dataserver.utils.base.exception;

import com.rlrg.dataserver.utils.common.ExceptionCodeEnum;

public class InvalidParamExeption extends BaseException {
    private static final long serialVersionUID = 1L;

    public InvalidParamExeption() {
        super(ExceptionCodeEnum.INVALID_PARAM);
    }

    public InvalidParamExeption(String message) {
        super(ExceptionCodeEnum.INVALID_PARAM, message);
    }

}
