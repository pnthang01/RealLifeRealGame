package com.rlrg.webserver.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.webserver.frontend.domain.UserProfile;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	public static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/getUserProfile", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public UserProfile getUserProfile(){
		try {
			UserProfile userProfile = new UserProfile();
			userProfile.setFirstName("Jsdf EEW");
			userProfile.setCompletedTask(300);
			return userProfile;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
