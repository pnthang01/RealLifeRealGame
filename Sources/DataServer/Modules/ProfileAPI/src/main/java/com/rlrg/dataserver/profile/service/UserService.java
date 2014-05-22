package com.rlrg.dataserver.profile.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.exception.LoginException;
import com.rlrg.dataserver.profile.form.LoginForm;
import com.rlrg.dataserver.profile.repository.UserRepository;
import com.rlrg.dataserver.utils.base.controller.BaseUtils;
import com.rlrg.dataserver.utils.base.exception.InvalidParamExeption;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;
import com.rlrg.dataserver.utils.base.service.CommonService;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CommonService commonService;

    @Transactional
    @Override
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
}
