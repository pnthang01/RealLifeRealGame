package com.rlrg.dataserver.language.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.base.service.ILanguageService;
import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;


@RequestMapping("/language")
@Controller
public class LanguageController extends BaseController{
	public static final Logger LOG = LoggerFactory.getLogger(LanguageController.class);
	
	@Autowired
	private ILanguageService<Language, LanguageDTO> languageService;
	
	@RequestMapping(value = "/getAllLanguages", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getAllLanguages(){
		String result = null;
		LOG.info("<< Starting webservice /language/getAllLanguages");
		try {		
			List<Language> list = languageService.getAllLanguage();
			//			
			result = languageService.encodeMutipleObjectsFromListT(list);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = languageService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = languageService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /language/getAllLanguages");
		return result;
	}
}
