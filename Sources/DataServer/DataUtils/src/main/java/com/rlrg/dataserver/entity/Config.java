package com.rlrg.dataserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rlrg.utillities.annotation.JsonExport;

@Entity
@Table(name = "config")
public class Config {

	@Id
	@Column(name = "key")
	@JsonExport(name = "Key")
	private String key;

	@Column(name = "value")
	@JsonExport(name = "Value")
	private String value;

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
