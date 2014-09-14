package com.rlrg.webserver.frontend.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.rlrg.webserver.frontend.helper.FieldMatch;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
		@FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match") })
public class SignUpForm {
	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}")
	private String username;

	@Length(min = 2, max = 20)
	private String firstname;

	@Email
	private String email;

	@Pattern(regexp = "^[a-z0-9_-]{6,18}$")
	private String password;

	@Pattern(regexp = "^[a-z0-9_-]{6,18}$")
	private String confirmPassword;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
