package com.team.service;

import com.team.dao.UserDAO;
import com.team.domain.User;
import com.team.exeption.InvalidParamExeption;
import com.team.exeption.LoginExeption;
import com.team.exeption.VException;
import com.team.param.LoginParam;
import com.team.util.VUtils;
import com.team.vo.UserVO;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional
public class UserServiceIpml implements UserService {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private CommonService commonService;

    @Override
    public UserVO login(LoginParam loginParam) throws VException {
        if (!StringUtils.isEmpty(loginParam.getUsername()) && !StringUtils.isEmpty(loginParam.getPassword())) {
            String username = loginParam.getUsername();
            String password = loginParam.getPassword();
            password = VUtils.md5(password);
            User user = userDao.login(username, password);
            if (user != null) {
                user.setLastLogin(new Date());
                return convertObject(commonService.setUserToken(user));
            } else {
                throw new LoginExeption("username or password is not correct!");
            }
        } else {
            throw new InvalidParamExeption("username or password is empty!");
        }
    }

    public UserVO convertObject(User user) {
        UserVO tmp = new UserVO();
        tmp.setToken(user.getToken());
        tmp.setFirstName(user.getFirstName());
        tmp.setLastName(user.getLastName());
        tmp.setUsername(user.getUsername());
        return tmp;
    }

    public List<UserVO> convertListObject(List<User> list) {
        return null;
    }

}
