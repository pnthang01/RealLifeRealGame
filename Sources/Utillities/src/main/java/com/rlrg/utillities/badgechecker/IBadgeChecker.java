package com.rlrg.utillities.badgechecker;

import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;

public interface IBadgeChecker {

	public void process(Long userId, AbstractCheckerDTO checkerDTO, String behaviour) throws Exception;
}
