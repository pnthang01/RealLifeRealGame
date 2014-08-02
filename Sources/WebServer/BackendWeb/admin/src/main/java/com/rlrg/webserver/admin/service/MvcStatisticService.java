package com.rlrg.webserver.admin.service;

import org.springframework.stereotype.Service;

import com.rlrg.dataserver.statistic.dto.StatisticDTO;

@Service
public class MvcStatisticService {

	public StatisticDTO getLoginStatistic(){
		StatisticDTO dto = new StatisticDTO();
        int[] values = new int[4];
        String[] labels = new String[4];
        //
        labels[0] = "Last 4 week";
        labels[1] = "Last 3 week";
        labels[2] = "Last 2 week";
        labels[3] = "Last week";
        //
        values[0] = 278823;
        values[1] = 652465;
        values[2] = 485756;
        values[3] = 555658;
        //
        dto.setLabels(labels);
        dto.setValues(values);
		return dto;
	}
}
