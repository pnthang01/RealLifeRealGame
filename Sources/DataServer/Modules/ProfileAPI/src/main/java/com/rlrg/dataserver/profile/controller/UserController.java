package com.rlrg.dataserver.profile.controller;

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
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.exception.LoginException;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private IUserService<User, UserDTO> userService;
    
	@RequestMapping(value = "/checkUsername", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
    public String checkUsername(@RequestParam(value="username", required=false) String username){
		String result = null;
		LOG.info("<< Starting webservice /user/checkUsername with parameter: username={}", username);
		try {
			boolean checking = userService.checkUsername(username);
			//
			result = userService.encodeRestObject(checking);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/checkUsername");
		return result;
    }
	
	@RequestMapping(value = "/getUserByToken", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
    public String isUserTokenExist(@RequestParam(value="token", required=false) String token){
		String result = null;
		LOG.info("<< Starting webservice /user/getUserByToken with parameter: token={}", token);
		try {
			UserDTO dto = userService.getUserDTOByToken(token);
			//
			result = userService.encodeSingleObjectFromVdto(dto);		
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/getUserByToken");
		return result;
    }

	@RequestMapping(value = "/signup", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
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
    
	@RequestMapping(value = "/updateProlile", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
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
	
	@RequestMapping(value = "/getAllUser", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
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
	
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value="username") String username, @RequestParam(value="password") String password) throws BaseException {
		String result = null;
		LOG.info("<< Starting webservice /user/login with parameter: usertname={}, password={}", username, "********");
		try {
			UserDTO userDTO = userService.login(username, password);
			//
			result = userService.encodeSingleObjectFromVdto(userDTO);
		} catch(LoginException e){
			RestObject restObject = RestObject.failBank(e.getErrorCode());
			result = userService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = userService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/login");
		return result;
    }
    
    @RequestMapping(value = "/logout", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
    @ResponseBody
    public String logout(@RequestParam(value="token") String token) throws BaseException {
		String result = null;
		LOG.info("<< Starting webservice /user/login with parameter: token={}", token);
		try {
			userService.logout(token);
			//
			result = userService.encodeBlankRestObject(RestObject.successBlank());
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = userService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /user/login");
		return result;
    }
    
    /**
	 * Search all users which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchUsers", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
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
