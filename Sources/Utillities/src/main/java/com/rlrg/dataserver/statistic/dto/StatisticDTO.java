package com.rlrg.dataserver.statistic.dto;

import java.util.Arrays;
import java.util.List;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Statistic")
public class StatisticDTO {

	@JsonExport(name = "Labels")
	private List<String> labels;

	@JsonExport(name = "Values")
	private List<Long> values;
	
	public StatisticDTO(){
	}

	public StatisticDTO(String[] labels, Long[] values) {
		super();
		this.labels = Arrays.asList(labels);
		this.values = Arrays.asList(values);
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Long> getValues() {
		return values;
	}

	public void setValues(List<Long> values) {
		this.values = values;
	}

}
