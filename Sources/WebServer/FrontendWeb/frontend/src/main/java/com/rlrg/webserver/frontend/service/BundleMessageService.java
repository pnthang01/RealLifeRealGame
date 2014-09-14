package com.rlrg.webserver.frontend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
public class BundleMessageService {
	
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(FieldError error, Locale locale){
		return messageSource.getMessage(error, locale);
	}
	
	public List<String> getMessages(List<FieldError> errors, Locale locale){
		List<String> messages = new ArrayList<String>();
		for(FieldError error : errors){
			messages.add(getMessage(error, locale));
		}
		return messages;
	}
}
