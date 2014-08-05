package com.rlrg.dataserver.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.controller.BaseUtils;
import com.rlrg.dataserver.base.domain.User;
import com.rlrg.dataserver.base.domain.UserToken;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.base.repository.CommonRepository;
import com.rlrg.dataserver.utillities.Constants;

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
    
    @Scheduled(cron="* 0/15 * * * ?")
	public void removeLongUsedToken() throws Exception{
		try {
			long currentTime = BaseUtils.truncateMiliSecondDate(new Date());
			//
			Iterator<String> iter = userTokens.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();
				UserToken userToken = userTokens.get(key);
				if(currentTime >= userToken.getTime() + Constants.TOKEN_TIMEOUT){
					userTokens.remove(key);
				}

			}
		} catch(Exception e){
			LOG.error("Error occurs when running removeLongUsedToken:", e);
			throw e;
		}
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
        String token = BaseUtils.hashPassword(user.getUsername(), BaseUtils.getCurrentTimeStamp());
        user.setToken(token);
        UserToken userTk = new UserToken();
        userTokens.put(token, userTk.convertUser2UserToken(user));
        commonRepo.update(user);
        return token;
    }
    
}
