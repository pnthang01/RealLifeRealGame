package com.rlrg.dataserver.profile.entity;

//Generated May 22, 2014 3:25:47 PM by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Permission generated by hbm2java
 */
@Entity
@Table(name = "permission", catalog = "rlrg")
public class Permission implements java.io.Serializable {

	private Integer id;
	private Role role;
	private String regional;

	public Permission() {
	}

	public Permission(Role role) {
		this.role = role;
	}

	public Permission(Role role, String regional) {
		this.role = role;
		this.regional = regional;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "regional", length = 45)
	public String getRegional() {
		return this.regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

}