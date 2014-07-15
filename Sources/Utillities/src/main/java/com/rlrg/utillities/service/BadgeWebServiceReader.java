package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class BadgeWebServiceReader extends BaseWebServiceReader<BadgeDTO> {
	
	private final String MODULE_NAME = "BadgeModule";
	
	private final String GET_ALL_BADGES_URL = "badge/getAllBadges?pageNumber={pageNumber}";
	private final String GET_ALL_BADGES_BY_STATUS_URL = "badge/getAllBadgesByStatus?status={status}&pageNumber={pageNumber}";
	private final String GET_BADGE_BY_ID = "badge/getBadgeById?id={id}";
	private final String UPDATE_BADGE_URL = "badge/updateBadge?restobject={restobject}";
	
	public ResultList<BadgeDTO> getAllBadges(Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_BADGES_URL, MODULE_NAME, pageNumber);
	}
	
	public ResultList<BadgeDTO> getAllBadgesByStatus(BadgeStatus status, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_BADGES_BY_STATUS_URL, MODULE_NAME, status, pageNumber);
	}
	
	public BadgeDTO getBadgeById(Integer id) throws RestClientException, ConvertException{
		return this.getAnObject(GET_BADGE_BY_ID, MODULE_NAME, id);
	}
	
	public boolean updateBadge(BadgeDTO dto) throws RestClientException, ConvertException{
		return this.postAnObjectT(UPDATE_BADGE_URL, MODULE_NAME, dto);
	}

	@Override
	protected Class<BadgeDTO> getTClass() {
		return BadgeDTO.class;
	}

}
