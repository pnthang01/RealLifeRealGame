package com.rlrg.dataserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.entity.Config;
import com.rlrg.dataserver.service.ConfigService;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/configuration")
@Controller
public class ConfigController extends BaseController{
	public static final Logger LOG = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/getConfig", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getConfig(@RequestParam(value = "key", required=true) String key){
		String result = null;
		LOG.info("<< Starting webservice /configuration/getConfig with parameters: key={}", key);
		try {		
			Config config = configService.getConfigByKey(key);
			//			
			result = configService.encodeSingleObjectFromVdto(config);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = configService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = configService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /configuration/getConfig");
		return result;
	}
	
	@RequestMapping(value = "/getConfigs", method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getConfigs(@RequestParam(value = "keys", required=true) List<String> keys){
		String result = null;
		LOG.info("<< Starting webservice /configuration/getConfigs with parameters: key={}", keys);
		try {		
			
			List<Config> configs = configService.getConfigsByKeys(keys);
			//			
			result = configService.encodeMutipleObjectsFromListV(configs);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = configService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = configService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /configuration/getConfigs");
		return result;
	}
}
