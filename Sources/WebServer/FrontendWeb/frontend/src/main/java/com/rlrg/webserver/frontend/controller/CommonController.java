package com.rlrg.webserver.frontend.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.exception.ExceptionCodeEnum;
import com.rlrg.webserver.frontend.form.LoginForm;
import com.rlrg.webserver.frontend.form.SignUpForm;
import com.rlrg.webserver.frontend.service.CommonService;
import com.rlrg.webserver.frontend.service.SessionService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
public class CommonController {
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value="/welcome")
	public String index(){
		return "index";	
	}
	
	@RequestMapping("/login")
	public String login(ModelMap model, HttpSession session){
		try {
			model.addAttribute("loginForm", new LoginForm());
			return "login";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@Valid LoginForm form, BindingResult bResult, HttpSession session, HttpServletResponse response){
		try {
			if (bResult.hasErrors()) {
				return "login";
			} else {
				UserDTO result = commonService.login(form.getUsername(), form.getPassword());			
				//
				session.setAttribute(Constants.SESSION_TOKEN, result.getToken());
				session.setAttribute(Constants.SESSION_FIRSTNAME, result.getFirstName());
				if(form.getRememberMe()){
					response.addCookie(new Cookie(Constants.COOKIE_TOKEN, result.getToken()));
				}
				//
				return "redirect:/home";
			}
		} catch (RestClientException | ConvertException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch(BaseException e){
			if(e.getMessage().equals(ExceptionCodeEnum.LOGIN_FAILED.getErrorCode())){
				bResult.addError(new ObjectError("*", "Tài khoản hoặc mật khẩu không chính xác."));
			}
			return "login";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(@CookieValue(value=Constants.COOKIE_TOKEN, required=false) String token, HttpSession session, HttpServletResponse response){
		try {
			session.removeAttribute(Constants.SESSION_TOKEN);
			session.removeAttribute(Constants.SESSION_FIRSTNAME);
			if(null != token && !token.isEmpty()){
				response.addCookie(new Cookie(Constants.COOKIE_TOKEN, null));
				commonService.logout(token);
			}
			return "redirect:/welcome";
		} catch (RestClientException | ConvertException e) {
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
	public String signup(@Valid SignUpForm signUpForm, BindingResult bResult, ModelMap model){
		try {
			if (bResult.hasErrors()) {
				return "signup";
			} else {
				String result = commonService.signup(signUpForm);
				if(result.equals("Success")){
					return "redirect:/welcome";
				} else {
					if(result.contains("constraint [email]")){
						bResult.addError(new ObjectError("email", "Email đã được sử dụng, vui lòng sử dụng email khác"));
					} else if (result.contains("constraint [username]")){
						bResult.addError(new ObjectError("username", "Username đã được sử dụng, vui lòng sử dụng username khác"));
					}				
					return "signup";
				}
			}
		} catch (RestClientException | ConvertException e) {
			LOG.error(e.getMessage());
			return "error";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}

}
