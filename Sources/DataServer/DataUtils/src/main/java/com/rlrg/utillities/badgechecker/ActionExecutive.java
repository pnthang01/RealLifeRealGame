package com.rlrg.utillities.badgechecker;

import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;
import com.rlrg.utillities.badgechecker.domain.IMainChecker;

public class ActionExecutive implements Runnable  {
	
	private String module;
	private Long userId;
	private AbstractCheckerDTO dto;
	private IMainChecker mainChecker;
	private String behaviour;
	
	public ActionExecutive(IMainChecker mainChecker, String module, Long userId, AbstractCheckerDTO dto, String behaviour) {
		this.mainChecker = mainChecker;
		this.module = module;
		this.userId = userId;
		this.dto = dto;
		this.behaviour = behaviour;
	}
	
	public void run() {
		mainChecker.mainProcess(module, userId, dto, behaviour);
	}

}
