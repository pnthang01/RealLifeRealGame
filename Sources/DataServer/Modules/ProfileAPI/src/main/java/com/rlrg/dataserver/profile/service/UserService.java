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

import com.rlrg.dataserver.base.controller.BaseUtils;
import com.rlrg.dataserver.base.controller.WebVariables;
import com.rlrg.dataserver.base.domain.UserToken;
import com.rlrg.dataserver.base.exception.InvalidParamExeption;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.CommonService;
import com.rlrg.dataserver.base.service.IRoleService;
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.profile.dto.RoleDTO;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.Role;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.entity.enums.UserStatus;
import com.rlrg.dataserver.profile.exception.LoginException;
import com.rlrg.dataserver.profile.repository.UserRepository;

@Service
public class UserService extends BaseService<User, UserDTO> implements IUserService<User, UserDTO>{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private IRoleService<Role, RoleDTO> roleService;
    
    @Autowired
    private CommonService commonService;

    /**
     * Get UserToken from system, then retrieve User in database bases on UserToken
     * @param token
     * @return
     * @throws UserTokenException
     */
    public User getUserByToken(String token) throws UserTokenException{
    	UserToken userToken = commonService.getUserToken(token);
    	return userRepo.findOne(userToken.getId());
    }
    
    /**
     * Get User by Id
     * @param userId
     * @return
     */
    public User getUserById(Long userId){
    	return userRepo.findOne(userId);
    }
    
    /**
     * Get User by Username
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
    	return userRepo.findUserByUsername(username);
    }
    
    /**
     * Create new User with data input is #UserDTO
     * @param dto
     * @throws Exception
     */
    @Transactional
    public void signup(UserDTO dto) throws Exception{
    	try{
    		Role defaultRole = roleService.getDefaultUserRole();
    		if(null == defaultRole){
				LOG.error("Cannot find default role.");
				throw new RepositoryException("Cannot find entity");
    		}
	    	User user = new User();
	    	user.setUsername(dto.getUsername());
	    	user.setPassword(dto.getPassword());
	    	user.setEmail(dto.getEmail());
	    	user.setFirstName(dto.getFirstName());
	    	user.setCreateDate(new Date());
	    	user.setRole(defaultRole);
	    	user.setStatus(UserStatus.CONFIRM);
	    	user.setPoint(0);
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
    		User user = this.getUserByToken(dto.getToken());
    		if(null == user){
				LOG.error("Cannot find User with Token:{}", dto.getToken());
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
    
    /**
     * Update #User's Role with token, which is can help to retrieve User's Id from Token System
     * @param username
     * @param token
     * @param roleId
     * @throws Exception
     */
    @Transactional
    public void updateUserRole(String token, Integer roleId) throws Exception{
    	try{
    		User user = this.getUserByToken(token);
    		Role role = roleService.getRoleById(roleId);
    		if(null == user || null == role){
				LOG.error("Cannot find User with Token:{} And/Or Cannot find Role with Id:{}", token, roleId);
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
    public UserDTO login(String username, String password) throws RepositoryException, InvalidParamExeption, Exception {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            password = BaseUtils.md5(password);
            User user = userRepo.login(username, password);
            if (user != null) {
                user.setLastLogin(new Date());
                com.rlrg.dataserver.base.domain.User uUser = convertUserToUtilUser(user);
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
    
    public boolean updateUserPerformed(Long userId, String performed){
    	return false;
    }
    
    /**
     * Update #User's point bases on token, which is can retrieve UserId from UserSystem
     * @param token
     * @param point
     * @throws Exception
     */
    @Transactional
    public void updateUserPoint(String token, Integer point) throws Exception{
    	try{
    		User user = this.getUserByToken(token);
    		if(null == user){
				LOG.error("Cannot find User with Token:{}", token);
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
    
	@Override
	public String getUserPerformedString(Long userId) {
		return userRepo.getUserPerformedString(userId);
	}
    
    private com.rlrg.dataserver.base.domain.User convertUserToUtilUser(User user){
    	com.rlrg.dataserver.base.domain.User uUser = new com.rlrg.dataserver.base.domain.User();
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
		return UserDTO.class;
	}

}
