package com.rlrg.webserver.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.StatisticWebServiceReader;

@Service
public class StatisticService {

	@Autowired
	private StatisticWebServiceReader statisticReader;
	
	public StatisticDTO getBasicUserStatistic(String token) throws RestClientException, ConvertException{
		return statisticReader.getBasicUserStatistic(token);
	}
}
