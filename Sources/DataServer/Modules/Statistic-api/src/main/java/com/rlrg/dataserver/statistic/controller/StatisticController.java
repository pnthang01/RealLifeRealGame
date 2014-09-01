package com.rlrg.dataserver.statistic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.service.IStatisticService;
import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/statistic")
@Controller
public class StatisticController {
	public static final Logger LOG = LoggerFactory.getLogger(StatisticController.class);
	
	@Autowired
	private IStatisticService<?, StatisticDTO> statisticService;
	
	@RequestMapping(value = "/getLoginStatistic", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getLoginStatistic(@RequestParam("months") Integer months){
		String result = null;
		LOG.info("<< Starting webservice /statistic/getLoginStatistic with parameters: months={}", months);
		try {
			StatisticDTO dto = statisticService.getLoginStatistic(months);
			//
			result = statisticService.encodeSingleObjectFromVdto(dto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = statisticService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = statisticService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /statistic/getLoginStatistic");
		return result;
	}
	
	@RequestMapping(value = "/getBasicUserStatistic", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getBasicUserStatistic(@RequestParam("token") String token){
		String result = null;
		LOG.info("<< Starting webservice /statistic/getBasicUserStatistic with parameters: token={}", token);
		try {
			StatisticDTO dto = statisticService.getBasicUserStatistic(token);
			//
			result = statisticService.encodeSingleObjectFromVdto(dto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = statisticService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = statisticService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /statistic/getBasicUserStatistic");
		return result;
	}
}
