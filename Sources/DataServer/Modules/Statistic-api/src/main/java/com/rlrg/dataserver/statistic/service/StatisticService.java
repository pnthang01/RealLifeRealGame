package com.rlrg.dataserver.statistic.service;

import java.util.Calendar;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.IStatisticService;
import com.rlrg.dataserver.base.service.IUserLogService;
import com.rlrg.dataserver.statistic.dto.StatisticDTO;

@Service
public class StatisticService extends BaseService<StatisticDTO, StatisticDTO> implements IStatisticService<StatisticDTO, StatisticDTO>{
	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserLogService<?> userLogService;

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
