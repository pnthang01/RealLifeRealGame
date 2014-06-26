package com.rlrg.dataserver.base.service;


public interface ICategoryLanguageService<T, V> extends IBaseService<T, V>{
	public T getCateLangByCateIdAndLangId(Integer categoryId, Integer languageId);

	public T save(T cl);
}
