package com.rlrg.utillities.badgechecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;

public interface IBadgeChecker {

	public void process(Long userId, AbstractCheckerDTO checkerDTO);
}
