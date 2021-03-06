package com.rlrg.dataserver.base.exception;

import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ExceptionCodeEnum;

public class InvalidParamExeption extends BaseException {
    private static final long serialVersionUID = 1L;

    public InvalidParamExeption() {
        super(ExceptionCodeEnum.INVALID_PARAM);
    }

    public InvalidParamExeption(String message) {
        super(ExceptionCodeEnum.INVALID_PARAM, message);
    }

}
