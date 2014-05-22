
package com.team.exeption;

import com.team.common.ExceptionCodeEnum;
import com.team.exeption.VException;

public class InvalidParamExeption extends VException {

    private static final long serialVersionUID = 1L;

    public InvalidParamExeption() {
        super(ExceptionCodeEnum.INVALID_PARAM);
    }

    public InvalidParamExeption(String message) {
        super(ExceptionCodeEnum.INVALID_PARAM, message);
    }

}
