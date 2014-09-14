package com.rlrg.webserver.frontend.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.webserver.frontend.service.TaskService;
import com.rlrg.webserver.frontend.service.UserService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
	public String home(@CookieValue(value=Constants.COOKIE_TOKEN, required=true, defaultValue="") String tokenCookie, HttpSession session,
			@RequestParam(value="pageNumber",defaultValue="1",required=false) Integer pageNumber, ModelMap model){
		try {
			Object tokenSession = session.getAttribute(Constants.SESSION_TOKEN);
			String activeToken = null;
			if(null == tokenSession){
				if(null == tokenCookie || tokenCookie.isEmpty()){
					return "redirect:/login"; 
				} 
				UserDTO dto = userService.getUserByToken(tokenCookie);
				if(null == dto){
					return "redirect:/login"; 
				} else {
					session.setAttribute(Constants.SESSION_TOKEN, dto.getToken());
					session.setAttribute(Constants.SESSION_FIRSTNAME, dto.getFirstName());
					activeToken = dto.getToken();
				}				
			} else {
				activeToken = tokenSession.toString();
			}
			ResultList<TaskDTO> today = taskService.getTasksByRangeTime(activeToken, 0, 0, pageNumber);
			ResultList<TaskDTO> future = taskService.getTasksByRangeTime(activeToken, 1, 6, pageNumber);			
			ResultList<TaskDTO> past = taskService.getTasksByRangeTime(activeToken, -7, 6, pageNumber);
			//
			model.addAttribute("today", today);			
			model.addAttribute("future", future);
			model.addAttribute("past", past);
			return "home";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "redirect:/login.do";
		}
	}
}
