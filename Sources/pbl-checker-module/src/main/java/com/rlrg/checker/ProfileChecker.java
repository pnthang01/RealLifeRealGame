package com.rlrg.checker;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.base.service.IAchievementService;
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

	private IAchievementService<Achievement, AchievementDTO> achievementService;

	@Override
	public void process(Long userId, AbstractCheckerDTO checkerDTO) throws Exception {
		if(null == badgeService){
			badgeService = factory.getBean("badgeService", IBadgeService.class);
		}
		if(null == userLogService){
			userLogService = factory.getBean("userLogService", IUserLogService.class);
		}
		if(null == achievementService){
			achievementService = factory.getBean("achievementService", IAchievementService.class);
		}
		//
		List<String> params = parsePropertiesToParams(checkerDTO);
		List<Badge> avaiBadges = badgeService.getBadgeByEligibility(userId, params);
		//
		for(Badge b : avaiBadges){
			checking(userId, b);
		}
	}
	
	private void checking(Long userId, Badge avaiBadge) throws Exception{
		JSONObject jObj = (JSONObject) JSONValue.parse(avaiBadge.getEligibility());
		if(avaiBadge.getEligibility().contains((BadgeCheckerConstants.LOGIN_ACTION))){
			Long count = userLogService.countUserLogByUserIdAndAction(userId, BadgeCheckerConstants.LOGIN_ACTION);
			if(count >= Long.valueOf(jObj.get(BadgeCheckerConstants.LOGIN_ACTION).toString())){
				achievementService.addAchievementForUser(userId, avaiBadge.getId());
			}
		}
	}
	
	private List<String> parsePropertiesToParams(AbstractCheckerDTO props){
		List<String> propList = props.getAllPropeties();
		//
		return propList;
	}

}
