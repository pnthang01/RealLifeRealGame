package com.rlrg.dataserver.profile.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.Role;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.entity.enums.UserStatus;
import com.rlrg.dataserver.profile.exception.LoginException;
import com.rlrg.dataserver.profile.form.LoginForm;
import com.rlrg.dataserver.profile.repository.UserRepository;
import com.rlrg.dataserver.utils.base.controller.BaseUtils;
import com.rlrg.dataserver.utils.base.controller.WebVariables;
import com.rlrg.dataserver.utils.base.exception.InvalidParamExeption;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;
import com.rlrg.dataserver.utils.base.service.BaseService;
import com.rlrg.dataserver.utils.base.service.CommonService;

@Service
public class UserService extends BaseService<User, UserDTO>{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private CommonService commonService;
    
    public User getUserById(Long userId){
    	return userRepo.findOne(userId);
    }
    
    @Transactional
    public void signup(UserDTO dto) throws Exception{
    	try{
    		Role defaultRole = roleService.getDefaultUserRole();
    		if(null == defaultRole){
				LOG.error("Cannot find default role.");
				throw new RepositoryException("Cannot find entity");
    		}
	    	User user = new User();
	    	user.setCreateDate(new Date());
	    	user.setEmail(dto.getEmail());
	    	user.setFirstName(dto.getFirstName());
	    	user.setRole(defaultRole);
	    	user.setStatus(UserStatus.CONFIRM);
	    	user.setUsername(dto.getUsername());
	    	//
	    	userRepo.save(user);
    	} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
    	}
    }
    
    @Transactional
    public void updateProfile(UserDTO dto) throws Exception{
    	try{
    		User user = userRepo.getUserByUsernameAndToken(dto.getUsername(), dto.getToken());
    		if(null == user){
				LOG.error("Cannot find User with Username: {} and Token:{}", dto.getUsername(), dto.getToken());
				throw new RepositoryException("Cannot find entity");
    		}
    		user.setFirstName(dto.getFirstName());
    		user.setLastName(dto.getLastName());
    		user.setPassword(dto.getPassword());
    		user.setGender(dto.getGender());
    		//
    		userRepo.save(user);
    	} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
    	}
    }
    
    @Transactional
    public void updateUserRole(String username, String token, Integer roleId) throws Exception{
    	try{
    		User user = userRepo.getUserByUsernameAndToken(username, token);
    		Role role = roleService.getRoleById(roleId);
    		if(null == user || null == role){
				LOG.error("Cannot find User with Username: {} and Token:{} And/Or Cannot find Role with Id:{}", username, token, roleId);
				throw new RepositoryException("Cannot find entity");
    		}
    		user.setRole(role);
    		//
    		userRepo.save(user);
    	} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
    	}
    }

    @Transactional
    public UserDTO login(LoginForm loginForm) throws RepositoryException, LoginException, InvalidParamExeption {
        if (!StringUtils.isEmpty(loginForm.getUsername()) && !StringUtils.isEmpty(loginForm.getPassword())) {
            String username = loginForm.getUsername();
            String password = loginForm.getPassword();
            password = BaseUtils.md5(password);
            User user = userRepo.login(username, password);
            if (user != null) {
                user.setLastLogin(new Date());
                com.rlrg.dataserver.utils.base.domain.User uUser = convertUserToUtilUser(user);
                user.setToken(commonService.setUserToken(uUser));
                //
                return convertObject(user);
            } else {
                throw new LoginException("username or password is not correct!");
            }
        } else {
            throw new InvalidParamExeption("username or password is empty!");
        }
    }
    
    @Transactional
    public void updateUserPoint(String username, String token, Integer point) throws Exception{
    	try{
    		User user = userRepo.getUserByUsernameAndToken(username, token);
    		if(null == user){
				LOG.error("Cannot find User with Username: {} and Token:{}", username, token);
				throw new RepositoryException("Cannot find entity");
    		}
    		user.setPoint(user.getPoint() + point);
    		//
    		userRepo.save(user);
    	} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
    	}
    }
    
    public List<UserDTO> getAllUserDTO(Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return userRepo.getAllUserDTO(pageRequest);
    }
    
    private com.rlrg.dataserver.utils.base.domain.User convertUserToUtilUser(User user){
    	com.rlrg.dataserver.utils.base.domain.User uUser = new com.rlrg.dataserver.utils.base.domain.User();
    	uUser.setId(user.getId());
    	uUser.setFirstName(user.getFirstName());
    	uUser.setUsername(user.getUsername());
    	uUser.setLastLogin(user.getLastLogin());
    	uUser.setToken(user.getToken());
    	//
    	return uUser;
    }

    public UserDTO convertObject(User user) {
    	UserDTO tmp = new UserDTO();
        tmp.setToken(user.getToken());
        tmp.setFirstName(user.getFirstName());
        tmp.setLastName(user.getLastName());
        tmp.setUsername(user.getUsername());
        return tmp;
    }

    public List<UserDTO> convertUsersToUserDTOs(List<User> list) {
        return null;
    }

	@Override
	public UserDTO convertEntityToDTO(User data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User revertDTOToEntity(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<UserDTO> getVClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
