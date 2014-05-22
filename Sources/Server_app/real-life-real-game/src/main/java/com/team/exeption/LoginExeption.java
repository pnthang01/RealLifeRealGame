/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team.exeption;

import com.team.common.ExceptionCodeEnum;

/**
 *
 * @author Anh Dinh
 */
public class LoginExeption extends VException{

    public LoginExeption() {
		super(ExceptionCodeEnum.LOGIN_FAILED);

	}

	public LoginExeption(String msg) {
		super(ExceptionCodeEnum.LOGIN_FAILED, msg);

	}
    
}
