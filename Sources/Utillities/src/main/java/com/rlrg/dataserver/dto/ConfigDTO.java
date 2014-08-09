package com.rlrg.dataserver.dto;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Configuration", pluralName = "Configurations")
public class ConfigDTO {
	
	@JsonExport(name = "Key")
	private String key;

	@JsonExport(name = "Value")
	private String value;
	
	public ConfigDTO(){
	}
	
	public ConfigDTO(String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
