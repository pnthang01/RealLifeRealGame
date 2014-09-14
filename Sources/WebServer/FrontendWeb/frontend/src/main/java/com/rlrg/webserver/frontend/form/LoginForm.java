package com.rlrg.webserver.frontend.form;

import javax.validation.constraints.Pattern;

public class LoginForm {

	@Pattern(regexp = "^[a-z0-9_-]{3,16}")
	private String username;
	
	@Pattern(regexp = "^[a-z0-9_-]{6,18}$")
	private String password;

	private boolean rememberMe=false;
	
	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
