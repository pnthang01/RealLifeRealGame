package com.rlrg.checker.dto;

import java.util.List;

import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;

public class TaskCheckerDTO extends AbstractCheckerDTO{
	private String category;
	private String diffLevel;

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDiffLevel() {
		return diffLevel;
	}
	public void setDiffLevel(String diffLevel) {
		this.diffLevel = diffLevel;
	}
	
	public List<String> getAllPropeties(){
		List<String> props = super.getAllPropeties();
		//props.add(action);
		props.add(category);
		props.add(diffLevel);
		//
		return props;
	}
}
