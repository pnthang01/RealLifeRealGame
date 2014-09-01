package com.rlrg.dataserver.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.controller.BaseUtils;
import com.rlrg.dataserver.base.domain.User;
import com.rlrg.dataserver.base.domain.UserToken;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.base.repository.CommonRepository;
import com.rlrg.dataserver.utillities.Utils;

@Service
public class CommonService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonService.class);
	
    @Autowired
    private CommonRepository commonRepo;
    
    private static Map<String, UserToken> userTokens;
    
    public CommonService(){
    	userTokens = new HashMap<String, UserToken>();
    }
    
    public int userTokensSize(){
    	return userTokens.size();
    }
    
    public Map<String, UserToken> getUserTokens(){
    	return userTokens;
    }

    /**
     * Get UserToken bases on token parameter
     * @param token
     * @return
     * @throws UserTokenException 
     */
    public UserToken getUserToken(String token) throws UserTokenException{
//    	UserToken userToken = userTokens.get(token);
//    	userToken.increaseTime(Constants.TOKEN_INCREASEMENT);
    	UserToken userToken = new UserToken(1l, "testacc", new Date().getTime()); // For testing //TODO
    	if(null == userToken || null == userToken.getId()){
			LOG.error("Cannot find UserToken with Token:{}", token);
			throw new UserTokenException();
    	} else {
        	return userToken;
    	}
    }

    /**
     * Update LastLogin and Token, then return the user's token
     * @param user
     * @return
     * @throws RepositoryException
     */
    public String setUserToken(User user) throws RepositoryException {
        String token = BaseUtils.hashPassword(user.getUsername(), Utils.getCurrentTimeStamp());
        user.setToken(token);
        UserToken userTk = new UserToken();
        userTokens.put(token, userTk.convertUser2UserToken(user));
        commonRepo.update(user);
        return token;
    }
    
}
