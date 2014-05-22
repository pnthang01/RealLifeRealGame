package com.rlrg.dataserver.utils.base.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.utils.base.controller.BaseUtils;
import com.rlrg.dataserver.utils.base.domain.User;
import com.rlrg.dataserver.utils.base.domain.UserToken;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;
import com.rlrg.dataserver.utils.base.repository.CommonRepository;

@Service
public class CommonService {
    @Autowired
    private CommonRepository commonRepo;

    private Map<String, UserToken> userToken = new HashMap<String, UserToken>();

    public Map<String, UserToken> getUserToken() {
        return userToken;
    }

    /**
     * Update LastLogin and Token, then return the user's token
     * @param user
     * @return
     * @throws RepositoryException
     */
    public String setUserToken(User user) throws RepositoryException {
        String token = BaseUtils.hashPassword(user.getUsername(), BaseUtils.getCurrentTimeStamp());
        user.setToken(token);
        UserToken userTk = new UserToken();
        this.userToken.put(token, userTk.convertUser2UserToken(user));
        commonRepo.update(user);
        return token;
    }
    
}
