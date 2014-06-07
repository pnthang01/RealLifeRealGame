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
import com.rlrg.dataserver.badge.service.AchievementService;
import com.rlrg.dataserver.utils.base.controller.BaseController;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/achievement")
@Controller
public class AchievementController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(AchievementController.class);

	@Autowired
	private AchievementService achievementService;
	
	@RequestMapping(value = "/getCategoriesByStatus", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String getUserAchivements(@RequestParam("username") String username, @RequestParam("pageNumber") Integer pageNumber) {
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
	public String addAchievement(@RequestParam("restobject") String json){
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
}
