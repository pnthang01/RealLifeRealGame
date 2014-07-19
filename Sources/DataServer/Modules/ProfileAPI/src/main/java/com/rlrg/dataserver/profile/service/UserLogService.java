package com.rlrg.dataserver.profile.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.IUserLogService;
import com.rlrg.dataserver.profile.entity.UserLog;
import com.rlrg.dataserver.profile.repository.UserLogRepository;

@Service
public class UserLogService extends BaseService<UserLog, UserLog> implements IUserLogService<UserLog>{

	@Autowired
	private UserLogRepository userLogRepo;
	
	public List<UserLog> getUserLogByUserId(Long userId){
		return userLogRepo.getUserLogByUserId(userId);
	}
	
    public void logUserAction(Long userId, String action){
    	UserLog userLog = new UserLog();
    	userLog.setAction(action);
    	userLog.setUserId(userId);
    	userLog.setTime(new Date());
    	//
    	userLogRepo.save(userLog);
    }
	
	@Override
	public UserLog convertEntityToDTO(UserLog data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserLog revertDTOToEntity(UserLog dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<UserLog> getVClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
