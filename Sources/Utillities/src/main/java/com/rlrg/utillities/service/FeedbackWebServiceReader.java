package com.rlrg.utillities.service;

import com.rlrg.dataserver.profile.dto.FeedbackDTO;
import com.rlrg.utillities.exception.ConvertException;

public class FeedbackWebServiceReader extends BaseWebServiceReader<FeedbackDTO>{
	
	private final String MODULE_NAME = "FeedbackModule";

	private final static String SAVE_FEEDBACK_URL = "feedback/saveFeedback?restobject={restobject}";
	
	public boolean saveFeedback(FeedbackDTO dto) throws ConvertException{
		return this.postAnObjectT(SAVE_FEEDBACK_URL, MODULE_NAME, dto);
	}
	
	@Override
	protected Class<FeedbackDTO> getTClass() {
		return FeedbackDTO.class;
	}

}
