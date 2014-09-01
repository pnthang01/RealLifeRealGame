package com.rlrg.dataserver.base.service;

import com.rlrg.dataserver.base.exception.UserTokenException;

public interface IStatisticService<T, V> extends IBaseService<T, V>{
	
	public V getLoginStatistic(int months);
	
	public V getBasicUserStatistic(String token) throws UserTokenException ;
}
