package com.rlrg.webserver.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.rlrg.dataserver.profile.dto.FeedbackDTO;
import com.rlrg.webserver.frontend.domain.Response;
import com.rlrg.webserver.frontend.service.BundleMessageService;
import com.rlrg.webserver.frontend.service.FeedbackService;

@Controller
public class FeedbackController {
	
	public static final Logger LOG = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	private BundleMessageService bundleMessage;
	
	@Autowired
	private CookieLocaleResolver localeResolver;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/feedback")
	@ResponseBody
	public Response submitFeedback(@Valid FeedbackDTO feedbackDTO, BindingResult bResult, HttpServletRequest request){
		Response response = new Response();
		try {
			if (bResult.hasErrors()) {
				response.setStatus(Response.FAIL);
				List<String> errorMessages = bundleMessage.getMessages(bResult.getFieldErrors(), localeResolver.resolveLocale(request));
				response.setResult(errorMessages);
			} else {
				feedbackService.createNewTask(feedbackDTO);
				response.setStatus(Response.SUCCESS);
			}	

		} catch (Exception e) {
			LOG.error(e.getMessage());
			response.setStatus(Response.FAIL);
			response.setResult(e.getMessage());
		}
		return response;
	}

}
