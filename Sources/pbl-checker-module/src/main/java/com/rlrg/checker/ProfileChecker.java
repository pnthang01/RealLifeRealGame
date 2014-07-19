package com.rlrg.checker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.dataserver.base.service.IUserLogService;
import com.rlrg.dataserver.profile.entity.UserLog;
import com.rlrg.utillities.badgechecker.BadgeCheckerConstants;
import com.rlrg.utillities.badgechecker.IBadgeChecker;
import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;

public class ProfileChecker implements IBadgeChecker{
	@Autowired 
	private AutowireCapableBeanFactory factory; 
	
	private IBadgeService<Badge, BadgeDTO> badgeService;
	
	private IUserLogService<UserLog> userLogService;
	

	@Override
	public void process(Long userId, AbstractCheckerDTO checkerDTO) {
		badgeService = factory.getBean("badgeService", IBadgeService.class);
		//
		List<String> params = parsePropertiesToParams(checkerDTO);
		List<Badge> avaiBadges = badgeService.getBadgeByEligibility(userId, params);
		//
		for(Badge b : avaiBadges){
			checking(userId, b);
		}
	}
	
	private void checking(Long userId, Badge avaiBadge){
		if(avaiBadge.getEligibility().contains(BadgeCheckerConstants.LOGIN_PROFILE)){
			userLogService = factory.getBean("userLogService", IUserLogService.class);
			//
			List<UserLog> userLogs = userLogService.getUserLogByUserId(userId);
		}
	}
	
	private List<String> parsePropertiesToParams(AbstractCheckerDTO props){
		List<String> propList = props.getAllPropeties();
		//
		return propList;
	}

}
