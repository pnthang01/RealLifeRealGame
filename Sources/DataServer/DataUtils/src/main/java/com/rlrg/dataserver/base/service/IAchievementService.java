package com.rlrg.dataserver.base.service;

import java.util.List;

import com.rlrg.dataserver.base.exception.UserTokenException;

public interface IAchievementService<T, V> extends IBaseService<T, V>{
	public Long countTimeBadgeBeAchieved(Integer badgeId);
	
	public List<V> getAchievementDTOs(String token, Integer pageNumber) throws UserTokenException;
	
	public Long countAchievements(String token) throws UserTokenException;
	
	public List<T> getAchievementsByUserId(Long userId);
	
	public List<V> getUserAchievementDTOs(String username, Integer pageNumber) throws UserTokenException;
	
	public void addAchievement(V dto) throws Exception;
	
	public void addAchievementForUser(Long userId, Integer badgeId) throws Exception ;
	
	public void deleteAchievement(Long achivementId);

	public List<Integer> getAllBadgeIdByUserId(Long userId);

}
