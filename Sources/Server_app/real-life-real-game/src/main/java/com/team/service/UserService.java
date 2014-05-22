

package com.team.service;

import com.team.exeption.VException;
import com.team.param.LoginParam;
import com.team.vo.UserVO;

public interface UserService {
   UserVO login(LoginParam loginParam) throws VException;
}
