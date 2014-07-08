package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.BadgeWebServiceReader;

@Service
public class MvcBadgeService {
	
	@Autowired
	private BadgeWebServiceReader badgeReader;

	public ResultList<BadgeDTO> getAllBadges(Integer pageNumber) throws RestClientException, ConvertException{
		return badgeReader.getAllBadges(pageNumber);
	}
	
	public ResultList<BadgeDTO> getAllBadgesByStatus(BadgeStatus status, Integer pageNumber) throws RestClientException, ConvertException{
		return badgeReader.getAllBadgesByStatus(status, pageNumber);
	}
	
	public boolean updateBadge(BadgeDTO dto) throws RestClientException, ConvertException{
		return badgeReader.updateBadge(dto);
	}
}
