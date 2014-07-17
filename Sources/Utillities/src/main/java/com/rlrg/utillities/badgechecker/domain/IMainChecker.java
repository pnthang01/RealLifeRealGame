package com.rlrg.utillities.badgechecker.domain;


public interface IMainChecker {
	public void mainProcess(String module, Long userId, AbstractCheckerDTO props);
}
