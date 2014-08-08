package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.utillities.exception.ConvertException;

public class StatisticWebServiceReader extends BaseWebServiceReader<StatisticDTO>{
	private final String MODULE_NAME = "StatisticModule";
	
	private final String GET_LOGIN_STATISTIC_URL = "statistic/getLoginStatistic?months={months}";
	
	public StatisticDTO getLoginStatistic(int months) throws RestClientException, ConvertException{
		return this.getAnObject(GET_LOGIN_STATISTIC_URL, MODULE_NAME, months);
	}

	@Override
	protected Class<StatisticDTO> getTClass() {
		return StatisticDTO.class;
	}
}
