package com.rlrg.dataserver.badge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.base.service.IAchievementService;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/achievement")
@Controller
public class AchievementController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(AchievementController.class);

	@Autowired
	private IAchievementService<Achievement, AchievementDTO> achievementService;
	
	@RequestMapping(value = "/getUserAchivements", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String getUserAchivements(@RequestParam(value="username", required=true) String username, @RequestParam("pageNumber") Integer pageNumber) {
		String result = null;
		LOG.info("<< Starting webservice /achievement/getUserAchivements with parameters: username={}, pageNumber={}", username, pageNumber);
		try {
			List<AchievementDTO> listDto = achievementService.getUserAchievementDTOs(username, pageNumber);
			//
			result = achievementService.encodeMutipleObjectsFromListV(listDto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = achievementService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = achievementService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /achievement/getUserAchivements");
		return result;
	}
	
	
	@RequestMapping(value = "/addAchievement", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String addAchievement(@RequestParam(value="restobject", required=true) String json){
		LOG.info("<< Starting webservice /achievement/addAchievement with parameters: restobject={}", json);
		String result = null;
		try{
			AchievementDTO dto = achievementService.decodeSingleObject(json);
			achievementService.addAchievement(dto);
			RestObject restObject = RestObject.successBlank();
			result = achievementService.encodeBlankRestObject(restObject);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = achievementService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = achievementService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /achievement/addAchievement");
		return result;
	}
	
	@RequestMapping(value = "/countTimeBadgeBeAchieved", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String countTimeBadgeBeAchieved(@RequestParam(value="badgeId", required=true) Integer badgeId){
		LOG.info("<< Starting webservice /achievement/countTimeBadgeBeAchieved with parameters: badgeId={}", badgeId);
		String result = null;
		try{
			Long count = achievementService.countTimeBadgeBeAchieved(badgeId);
			//
			result = achievementService.encodeCountingRestObject(count, "/achievement/countTimeBadgeBeAchieved");
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = achievementService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = achievementService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /achievement/countTimeBadgeBeAchieved");
		return result;
	}
	
	/**
	 * Search all achievements which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchAchievements", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String searchAchievementsByKeyword(@RequestParam(value="keyword", required=true) String keyword, 
			@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /achievement/searchAchievements with parameters: keyword={}, pageNumber={}", keyword, pageNumber);
		try {
			List<AchievementDTO> listDTO = achievementService.searchAchievementsByKeyword(keyword, pageNumber);
			Long total = achievementService.countAchievementsByKeyword(keyword);
			//
			result = achievementService.encodeMutipleObjectsFromListV(listDTO, total);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = achievementService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = achievementService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /achievement/searchAchievements");
		return result;
	}
}
