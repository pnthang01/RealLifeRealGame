package com.rlrg.dataserver.base.service;

import java.util.List;

public interface ILanguageService<T, V> extends IBaseService<T, V> {

	public T getLanguageByI18N(String i18n);

	public List<T> getAllLanguage();
	
	public List<V> searchLanguagesByKeyword(String keyword, Integer pageNumber);

	public Long countLanguagesByKeyword(String keyword);
}
