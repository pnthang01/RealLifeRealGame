package com.rlrg.dataserver.statistic.service;

import java.util.Calendar;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.domain.UserToken;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.CommonService;
import com.rlrg.dataserver.base.service.IStatisticService;
import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.base.service.IUserLogService;
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;

@Service
public class StatisticService extends BaseService<StatisticDTO, StatisticDTO> implements IStatisticService<StatisticDTO, StatisticDTO>{
	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserLogService<?> userLogService;
	
	@Autowired
	private ITaskService<Task, TaskDTO> taskService;
	
	@Autowired
    private IUserService<User, UserDTO> userService;
	
	@Autowired
	private CommonService commonService;

	@Override
	public StatisticDTO getLoginStatistic(int months) {
		Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        Long[] values = new Long[months];
        String[] labels = new String[months];
        for (int i = 1; i <= months; i++) {
            if (currentMonth < 0) {
                currentMonth = 11;
                currentYear--;
            }
            labels[months - i] = (currentMonth + 1) + "/" + currentYear;
            values[months - i] = userLogService.countLoginActionByTime(currentMonth + 1, currentYear);
            currentMonth--;
        }
		//
		return new StatisticDTO(labels, values);
	}
	

	@Override
	public StatisticDTO getBasicUserStatistic(String token) throws UserTokenException {
		UserToken userToken = commonService.getUserToken(token);
		Long taskCompleted = taskService.countTaskByStatus(userToken.getId(), TaskStatus.COMPLETED);
		//
		Long point = Long.valueOf(userService.getUserPoint(token).toString());
		//
		StatisticDTO dto = new StatisticDTO();
		dto.addStatistic("TaskCompleted", taskCompleted);
		dto.addStatistic("Point", point);
		//
		return dto;
	}


	@Override
	public StatisticDTO convertEntityToDTO(StatisticDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatisticDTO revertDTOToEntity(StatisticDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<StatisticDTO> getVClass() {
		return StatisticDTO.class;
	}

}
