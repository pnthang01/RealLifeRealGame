package com.rlrg.dataserver.scheduler;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.rlrg.dataserver.base.controller.BaseUtils;
import com.rlrg.dataserver.base.domain.UserToken;
import com.rlrg.dataserver.base.service.CommonService;
import com.rlrg.dataserver.utillities.Constants;

public class CommonScheduler {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonScheduler.class);
	
	@Autowired
	private CommonService commonService;
    
    @Scheduled(cron="* 0/15 * * * ?")
	public void removeLongUsedToken() throws Exception{
		try {
			Map<String, UserToken> userTokens = commonService.getUserTokens();
			long currentTime = BaseUtils.truncateMiliSecondDate(new Date());
			//
			Iterator<String> iter = userTokens.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();
				UserToken userToken = userTokens.get(key);
				if(currentTime >= userToken.getTime() + Constants.TOKEN_TIMEOUT){
					synchronized (userToken) {
						userTokens.remove(key);
					}
				}
			}
		} catch(Exception e){
			LOG.error("Error occurs when running removeLongUsedToken:", e);
			throw e;
		}
	}
    
}
