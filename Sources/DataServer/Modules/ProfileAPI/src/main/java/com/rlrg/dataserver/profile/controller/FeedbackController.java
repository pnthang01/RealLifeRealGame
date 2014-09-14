package com.rlrg.dataserver.profile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.profile.dto.FeedbackDTO;
import com.rlrg.dataserver.profile.service.FeedbackService;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	private static final Logger LOG = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * Get all roles in database
	 * @return
	 */
	@RequestMapping(value = "/saveFeedback", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String saveFeedback(@RequestParam("restobject") String json){
		String result = null;
		LOG.info("<< Starting webservice /feedback/saveFeedback with parameters: restobject={}", json);
		try {
			FeedbackDTO data = feedbackService.decodeSingleObject(json);
			boolean saved = feedbackService.save(data);
			//
			result = feedbackService.encodeRestObject(saved);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = feedbackService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = feedbackService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /feedback/saveFeedback");
		return result;
	}
}
