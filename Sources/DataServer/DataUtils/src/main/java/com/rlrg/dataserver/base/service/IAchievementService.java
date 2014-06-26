package com.rlrg.dataserver.base.service;

import java.util.List;

import com.rlrg.dataserver.base.exception.UserTokenException;

public interface IAchievementService<T, V> extends IBaseService<T, V>{
	public Long countTimeBadgeBeAchieved(Integer badgeId);
	
	public List<V> getUserAchievementDTOs(String username, Integer pageNumber) throws UserTokenException;
	
	public void addAchievement(V dto) throws Exception;
}
