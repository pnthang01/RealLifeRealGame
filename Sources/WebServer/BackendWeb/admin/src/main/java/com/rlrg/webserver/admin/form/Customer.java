package com.rlrg.webserver.admin.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Customer {

	@NotEmpty //make sure name is not empty
	private String name;
 
	@Range(min = 1, max = 150) //age need between 1 and 150
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
 
	//getter and setter methods
}
