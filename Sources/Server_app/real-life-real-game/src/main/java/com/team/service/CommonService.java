package com.team.service;

import com.team.common.UserToken;
import com.team.dao.UserDAO;
import com.team.domain.User;
import com.team.exeption.DaoExeption;
import com.team.util.VUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    private UserDAO userDao;

    private Map<String, UserToken> userToken = new HashMap<String, UserToken>();

    public Map<String, UserToken> getUserToken() {
        return userToken;
    }

    public User setUserToken(User user) throws DaoExeption {
        String token = VUtils.hashPassword(user.getUsername(), VUtils.getCurrentTimeStamp());
        user.setToken(token);
        UserToken userTk = new UserToken();
        this.userToken.put(token, userTk.convertUser2UserToken(user));
        userDao.update(user);
        return user;
    }

}
