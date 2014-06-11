package com.rlrg.dataserver.profile.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.form.LoginForm;
import com.rlrg.dataserver.profile.service.UserService;
import com.rlrg.dataserver.utils.base.controller.BaseController;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService userService;

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
	
	@RequestMapping(value = "/getAllUser", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
    public String getAllUser(@RequestParam("pageNumber") Integer pageNumber){
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
    
    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public RestObject login(@RequestBody LoginForm loginParam) throws BaseException {
        UserDTO userDTO = userService.login(loginParam);
        return RestObject.fromData(userDTO);
    }
}
