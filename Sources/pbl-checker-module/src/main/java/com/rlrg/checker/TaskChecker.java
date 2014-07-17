package com.rlrg.checker;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.rlrg.checker.dto.TaskCheckerDTO;
import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.utillities.badgechecker.IBadgeChecker;
import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;

public class TaskChecker implements IBadgeChecker{
	private static final Logger LOG = LoggerFactory.getLogger(TaskChecker.class);

	@Autowired 
	private AutowireCapableBeanFactory factory; 
	
	private IBadgeService<Badge, BadgeDTO> badgeService;

	@SuppressWarnings("unchecked")
	public void process(Long userId, AbstractCheckerDTO checkerDTO) {
		badgeService = factory.getBean("badgeService", IBadgeService.class);
		//
		List<String> params = parsePropertiesToParams(checkerDTO);
		List<Badge> avaiBadges = badgeService.getBadgeByEligibility(userId, params);
		//
		for(Badge b : avaiBadges){
			System.out.println(b.getId());
		}
	}
	
	private List<String> parsePropertiesToParams(AbstractCheckerDTO props){
		TaskCheckerDTO taskCheckerDTO = (TaskCheckerDTO) props;
		List<String> propList = taskCheckerDTO.getAllPropeties();
		//
		return propList;
	}
	
}
