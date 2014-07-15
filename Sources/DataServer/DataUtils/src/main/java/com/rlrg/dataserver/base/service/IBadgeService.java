package com.rlrg.dataserver.base.service;

import java.util.List;

import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.dataserver.base.exception.RepositoryException;

public interface IBadgeService<T, V> extends IBaseService<T, V>{
	
	public T findBadgeById(Integer badgeId);
	
	public List<V> getAllBadges(Integer pageNumber);	
	
	public V getBadgeById(Integer id);
	
	public Long countAllBadges();
	
	public List<V> getBadgesByStatus(BadgeStatus status, Integer pageNumber);
			
	public void update(V dto) throws RepositoryException;
	
	public List<V> searchBadgesByKeyword(String keyword, Integer pageNumber);
	
	public Long countBadgesByKeyword(String keyword);

}
