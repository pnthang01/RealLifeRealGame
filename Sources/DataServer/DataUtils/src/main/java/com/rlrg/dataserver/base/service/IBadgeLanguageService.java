package com.rlrg.dataserver.base.service;


public interface IBadgeLanguageService<T, V> extends IBaseService<T, V> {
	public T getBadgeLangByBadgeIdAndLangId(Integer badgeId, Integer languageId);

	public T save(T bl);
	
}
