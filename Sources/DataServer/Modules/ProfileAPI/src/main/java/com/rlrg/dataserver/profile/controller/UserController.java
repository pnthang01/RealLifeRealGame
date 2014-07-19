package com.rlrg.dataserver.profile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.form.LoginForm;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private IUserService<User, UserDTO> userService;

	@RequestMapping(value = "/signup", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
    public String signupNewUser(@RequestParam("restobject") String json){
		String result = null;
		LOG.info("<< Starting webservice /user/signup with parameters: restobject={}", json);
		try {
			UserDTO dto = userService.decodeSingleObject(json);
			userService.signup(dto);
			RestObject restObject = RestObject.successBlank();
			result = userService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/signup");
		return result;
    }
    
	@RequestMapping(value = "/updateProlile", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
    public String updateProfile(@RequestParam("restobject") String json){
		String result = null;
		LOG.info("<< Starting webservice /user/signup with parameters: restobject={}", json);
		try {
			UserDTO dto = userService.decodeSingleObject(json);
			userService.updateProfile(dto);
			RestObject restObject = RestObject.successBlank();
			result = userService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/signup");
		return result;
    }
	
	@RequestMapping(value = "/getAllUser", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
    public String getAllUser(@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /user/getAllUser with parameter: pageNumber={}", pageNumber);
		try {
			List<UserDTO> listDto = userService.getAllUserDTO(pageNumber);
			//
			result = userService.encodeMutipleObjectsFromListV(listDto);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/getAllUser");
		return result;
    }
    
//	
//	@RequestMapping(value = "/updateUserRole", produces = "application/json", method=RequestMethod.POST)
//	@ResponseBody
//    public String updateUserRole(@RequestParam("token") String token, @RequestParam("roleId") Integer roleId){
//		String result = null;
//		LOG.info("<< Starting webservice /user/updateUserRole with parameters: token={}, roleId={}", token, roleId);
//		try {
//			userService.updateUserRole(token, roleId);
//			RestObject restObject = RestObject.successBlank();
//			result = userService.encodeBlankRestObject(restObject);
//		} catch(BaseException e){
//			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
//			result = userService.encodeBlankRestObject(restObject);
//		} catch(Exception e){
//			RestObject restObject = RestObject.failBank(e.getMessage());
//			result = userService.encodeBlankRestObject(restObject);
//		}
//		LOG.info("<< End webservice /user/updateUserRole");
//		return result;
//    }
    
    @RequestMapping(value = "/login", produces = "application/json", method=RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value="username") String username, @RequestParam(value="password") String password) throws BaseException {
		String result = null;
		LOG.info("<< Starting webservice /user/getAllUser with parameter: usertname={}, password={}", username, "********");
		try {
			UserDTO userDTO = userService.login(username, password);
			//
			result = userService.encodeSingleObjectFromVdto(userDTO);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/getAllUser");
		return result;
    }
    
    /**
	 * Search all users which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchUsers", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String searchUsersByKeyword(@RequestParam(value="keyword", required=true) String keyword, 
			@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /user/searchUsers with parameters: keyword={}, pageNumber={}", keyword, pageNumber);
		try {
			List<UserDTO> listDTO = userService.searchUsersByKeyword(keyword, pageNumber);
			Long total = userService.countUsersByKeyword(keyword);
			//
			result = userService.encodeMutipleObjectsFromListV(listDTO, total);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/searchUsers");
		return result;
	}
}
