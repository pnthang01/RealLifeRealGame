package com.rlrg.dataserver.badge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping(value = "/badge")
@Controller
public class BadgeController extends BaseController{
	
	public static final Logger LOG = LoggerFactory.getLogger(BadgeController.class);
	
	@Autowired
	private IBadgeService<Badge, BadgeDTO> badgeService;
	
	/**
	 * Get all {@link #Badge} with paging
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/getAllBadges", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getAllBadges(@RequestParam(value="pageNumber") Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /badge/getAllBadges with parameters: pageNumber={}", pageNumber);
		try {
			List<BadgeDTO> listDto = badgeService.getAllBadges(pageNumber);
			Long total = badgeService.countAllBadges();
			//
			result = badgeService.encodeMutipleObjectsFromListV(listDto, total);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = badgeService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = badgeService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /badge/getAllBadges");
		return result;
	}
	
	@RequestMapping(value = "/getBadgeById", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getBadgeById(@RequestParam(value="id") Integer id){
		String result = null;
		LOG.info("<< Starting webservice /category/getCategoryByCode with parameters: id={}, pageNumber={}", id);
		try {
			BadgeDTO dto = badgeService.getBadgeById(id);
			//
			result = badgeService.encodeSingleObjectFromVdto(dto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = badgeService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = badgeService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /category/getCategoryByCode");
		return result;
	}
	
	/**
	 * Get all #Badge with their status and paging
	 * @param status
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/getBadgesByStatus", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getBadgesByStatus(@RequestParam(value="status", required=true) BadgeStatus status,
			@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /badge/getBadgesByStatus with parameters: status={}, pageNumber={}", status.name(), pageNumber);
		try {
			List<BadgeDTO> listDto = badgeService.getBadgesByStatus(status, pageNumber);
			//
			result = badgeService.encodeMutipleObjectsFromListV(listDto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = badgeService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = badgeService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /badge/getBadgesByStatus");
		return result;
	}
	
	@RequestMapping(value = "/updateBadge", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public String updateBadge(@RequestParam(value="restobject", required=true) String json){
		String result = null;
		LOG.info("<< Starting webservice /badge/updateBadge with parameters: restobject={}", json);
		try {
			BadgeDTO dto = badgeService.decodeSingleObject(json);
			badgeService.update(dto);
			RestObject restObject = RestObject.successBlank();
			result =  badgeService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = badgeService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = badgeService.encodeBlankRestObject(restObject);
		}
		//
		LOG.info("<< End webservice /badge/updateBadge");
		return result;
	}
	
	/**
	 * Search all badges which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchBadges", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String searchBadgesByKeyword(@RequestParam(value="keyword", required=true) String keyword, 
			@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /badge/searchBadges with parameters: keyword={}, pageNumber={}", keyword, pageNumber);
		try {
			List<BadgeDTO> listDTO = badgeService.searchBadgesByKeyword(keyword, pageNumber);
			Long total = badgeService.countBadgesByKeyword(keyword);
			//
			result = badgeService.encodeMutipleObjectsFromListV(listDTO, total);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = badgeService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = badgeService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /badge/searchBadges");
		return result;
	}
}
