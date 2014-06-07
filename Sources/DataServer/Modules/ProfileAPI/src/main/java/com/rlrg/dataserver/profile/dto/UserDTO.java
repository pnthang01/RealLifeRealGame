package com.rlrg.dataserver.profile.dto;

import java.util.Date;

import com.rlrg.dataserver.profile.entity.enums.UserGender;
import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "User", pluralName = "Users")
public class UserDTO {
	@JsonExport(name = "Username")
	private String username;

	@JsonExport(name = "Password")
	private String password;

	@JsonExport(name = "Email")
	private String email;

	@JsonExport(name = "FirstName")
	private String firstName;

	@JsonExport(name = "LastName")
	private String lastName;
	
	@JsonExport(name = "LastLogin")
	private Date lastLogin;

	@JsonExport(name = "Gender")
	private UserGender gender;

	@JsonExport(name = "Token")
	private String token;
	
	public UserDTO(){
	}
	
	public UserDTO(String username, String firstName, String email, Date lastLogin, UserGender gender){
		this.username = username;
		this.firstName = firstName;
		this.email = email;
		this.lastLogin = lastLogin;
		this.gender = gender;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
