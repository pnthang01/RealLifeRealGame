package com.rlrg.dataserver.language.dto;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Language", pluralName = "Languages")
public class LanguageDTO {
	@JsonExport(name = "ID")
	private Integer id;

	@JsonExport(name = "LanguageName")
	private String language;

	@JsonExport(name = "Country")
	private String country;

	@JsonExport(name = "I18N")
	private String i18n;
	
	public LanguageDTO(){
	}

	public LanguageDTO(Integer id, String language, String country, String i18n) {
		super();
		this.id = id;
		this.language = language;
		this.country = country;
		this.i18n = i18n;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getI18n() {
		return i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

}
