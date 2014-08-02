package com.rlrg.dataserver.base.service;

public interface IStatisticService<T, V> extends IBaseService<T, V>{
	
	public V getLoginStatistic(int months);
}
