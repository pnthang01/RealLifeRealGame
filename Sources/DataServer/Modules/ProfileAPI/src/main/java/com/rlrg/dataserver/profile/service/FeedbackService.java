package com.rlrg.dataserver.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.profile.dto.FeedbackDTO;
import com.rlrg.dataserver.profile.entity.Feedback;
import com.rlrg.dataserver.profile.repository.FeedbackRepository;

@Service
public class FeedbackService extends BaseService<Feedback, FeedbackDTO>{

	@Autowired
	private FeedbackRepository feedbackRepo;
	
	public boolean save(FeedbackDTO feedback){
		Feedback entity = this.revertDTOToEntity(feedback);
		return feedbackRepo.save(entity).getId() != null;
	}

	@Override
	public FeedbackDTO convertEntityToDTO(Feedback data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback revertDTOToEntity(FeedbackDTO dto) {
		Feedback entity = new Feedback();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setMessage(dto.getMessage());
		entity.setName(dto.getName());
		entity.setTitle(dto.getTitle());
		return entity;
	}

	@Override
	protected Class<FeedbackDTO> getVClass() {
		return FeedbackDTO.class;
	}



}