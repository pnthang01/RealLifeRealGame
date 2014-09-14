package com.rlrg.webserver.frontend.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.profile.dto.FeedbackDTO;
import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.service.FeedbackWebServiceReader;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackWebServiceReader feedbackReader;

	public boolean createNewTask(FeedbackDTO feedback) throws ParseException, UnsupportedEncodingException, BaseException{
		//
		return feedbackReader.saveFeedback(feedback);
	}
}
