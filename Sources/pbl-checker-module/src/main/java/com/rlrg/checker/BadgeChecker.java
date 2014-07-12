package com.rlrg.checker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.utillities.badgechecker.IBadgeChecker;

public class BadgeChecker implements IBadgeChecker{
	private static final Logger LOG = LoggerFactory.getLogger(BadgeChecker.class);
	
	@Autowired
	private IBadgeService badgeService;
	
	public void process(String action, Long userId, Object[] props) {
		//badgeService.getBadgeByEligibility(userId, params);
	}
	
}
