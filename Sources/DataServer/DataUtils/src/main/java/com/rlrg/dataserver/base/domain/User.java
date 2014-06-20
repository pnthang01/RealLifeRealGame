package com.rlrg.dataserver.base.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="domain.User")
@Table(name = "user")
//@NamedQuery(name = "user.Update", query = "UPDATE User u SET u.token = :token WHERE u.id = :id")
public class User {
	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = false)
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "token", length = 45)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login", length = 19)
	private Date lastLogin;

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
