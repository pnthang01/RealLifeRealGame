package com.rlrg.dataserver.statistic.dto;

public class StatisticDTO {
    private String[] labels;
    private Long[] values;
    
    public StatisticDTO(String[] labels, Long[] values){
    	this.labels = labels;
    	this.values = values;
    }
    
    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

	public Long[] getValues() {
		return values;
	}

	public void setValues(Long[] values) {
		this.values = values;
	}
}
