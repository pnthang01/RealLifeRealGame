package com.rlrg.checker;


import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.config.Task;

import com.rlrg.checker.dto.TaskCheckerDTO;
import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.base.service.IAchievementService;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.badgechecker.BadgeCheckerConstants;
import com.rlrg.utillities.badgechecker.IBadgeChecker;
import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;

public class TaskChecker implements IBadgeChecker{
	private static final Logger LOG = LoggerFactory.getLogger(TaskChecker.class);

	@Autowired 
	private AutowireCapableBeanFactory factory; 
	
	private IBadgeService<Badge, BadgeDTO> badgeService;
	
	private ITaskService<Task, TaskDTO> taskService;
	
	private IAchievementService<Achievement, AchievementDTO> achievementService;

	/**
	 * Is checking means checking user action's to give badge, otherwise reverse achievement if needed
	 * @param userId
	 * @param checkerDTO
	 * @param isChecking
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void process(Long userId, AbstractCheckerDTO checkerDTO, String behaviour) throws Exception {
		if(null == badgeService){
			badgeService = factory.getBean("badgeService", IBadgeService.class);
		}
		if(null == taskService){
			taskService = factory.getBean("taskService", ITaskService.class);
		}
		if(null == achievementService){
			achievementService = factory.getBean("achievementService", IAchievementService.class);
		}
		badgeService = factory.getBean("badgeService", IBadgeService.class);
		//
		List<String> params = parsePropertiesToParams(checkerDTO);
		if(BadgeCheckerConstants.CHECKING.equals(behaviour)){
			List<Badge> avaiBadges = badgeService.getBadgeByEligibility(userId, params);
			//
			for(Badge b : avaiBadges){
				checking(userId, ((TaskCheckerDTO) checkerDTO).getCategory(),  b);
			}
		} else if(BadgeCheckerConstants.UNCHECKING.equals(behaviour)) {
			List<Achievement> achivements = achievementService.getAchievementsByUserId(userId);
			//
			for(Achievement a : achivements){
				unchecking(userId, ((TaskCheckerDTO) checkerDTO).getCategory(),  a);
			}
		} else {
			LOG.debug("NO behaviour is defined. TaskChecker is completed without changing data.");
		}
	}
	
	private void unchecking(Long userId, String cateTag, Achievement achievement) throws Exception{
		Badge badge = achievement.getBadge();
		JSONObject jObj = (JSONObject) JSONValue.parse(badge.getEligibility());
		boolean isDeleted = false;
		if(badge.getEligibility().contains((BadgeCheckerConstants.COMPLETE_TASK)) && 
				badge.getEligibility().contains((cateTag))){
			Long count = taskService.countTotalCompletedTaskByUserIdAndCateTag(userId, cateTag);
			String tempTag = new StringBuilder(BadgeCheckerConstants.COMPLETE_TASK).append("_").append(cateTag).toString();
			//
			if(count < Long.valueOf(jObj.get(tempTag).toString())){
				isDeleted = true;
			}
		}
		//
		if(isDeleted)
			achievementService.deleteAchievement(achievement.getId());
	}
	
	private void checking(Long userId, String cateTag, Badge avaiBadge) throws Exception{
		JSONObject jObj = (JSONObject) JSONValue.parse(avaiBadge.getEligibility());
		if(avaiBadge.getEligibility().contains((BadgeCheckerConstants.COMPLETE_TASK)) && 
				avaiBadge.getEligibility().contains((cateTag))){
			Long count = taskService.countTotalCompletedTaskByUserIdAndCateTag(userId, cateTag);
			String tempTag = new StringBuilder(BadgeCheckerConstants.COMPLETE_TASK).append("_").append(cateTag).toString();
			//
			if(count == Long.valueOf(jObj.get(tempTag).toString())){
				achievementService.addAchievementForUser(userId, avaiBadge.getId());
			}
		}
	}
	
	
	private List<String> parsePropertiesToParams(AbstractCheckerDTO props){
		TaskCheckerDTO taskCheckerDTO = (TaskCheckerDTO) props;
		List<String> propList = taskCheckerDTO.getAllPropeties();
		//
		return propList;
	}
	
}
