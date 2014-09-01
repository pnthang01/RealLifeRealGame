package com.rlrg.webserver.frontend.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.webserver.frontend.form.LoginForm;
import com.rlrg.webserver.frontend.form.SignUpForm;
import com.rlrg.webserver.frontend.service.CommonService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
public class CommonController {
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(value="/welcome")
	public String index(){
		return "index";	
	}
	
	@RequestMapping("/login")
	public String login(ModelMap model){
		try {
			model.addAttribute("loginForm", new LoginForm());
			return "login";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@Valid LoginForm form, BindingResult bResult, HttpServletResponse response){
		try {
			if (bResult.hasErrors()) {
				return "login";
			} else {
				UserDTO result = commonService.login(form.getUsername(), form.getPassword());
				response.addCookie(new Cookie(Constants.PROFILE_TOKEN, result.getToken()));
				response.addCookie(new Cookie(Constants.PROFILE_FIRSTNAME, result.getFirstName()));
				return "redirect:/home";
			}
		} catch (RestClientException | IllegalStateException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/signup")
	public String signup(ModelMap model){	
		try {
			model.addAttribute("signUpForm", new SignUpForm());
			return "signup";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(@Valid SignUpForm form, BindingResult bResult){
		try {
			if (bResult.hasErrors()) {
				return "signup";
			} else {
				boolean result = commonService.signup(form);
				if(result){
					return "redirect:/welcome";
				} else {
					return "signup";
				}
			}
		} catch (RestClientException | IllegalStateException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}

}
