package com.rlrg.dataserver.profile.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.form.LoginForm;
import com.rlrg.dataserver.profile.service.UserService;
import com.rlrg.dataserver.utils.base.controller.BaseController;
import com.rlrg.dataserver.utils.base.exception.BaseException;
import com.rlrg.utillities.domain.RestObject;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public RestObject login(@RequestBody LoginForm loginParam) throws BaseException {
        UserDTO userDTO = userService.login(loginParam);
        return RestObject.fromData(userDTO);
    }
}
