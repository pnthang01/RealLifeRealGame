package com.team.controller;

import com.team.exeption.VException;
import com.team.param.LoginParam;
import com.team.service.UserService;
import com.team.vo.RestObject;
import com.team.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController extends VController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login_web", method = POST)
    @ResponseBody
    public RestObject login(@RequestBody LoginParam loginParam) throws VException {
        UserVO userVO = userService.login(loginParam);
        return RestObject.fromData(userVO);
    }
}
