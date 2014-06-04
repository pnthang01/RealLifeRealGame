package com.rlrg.dataserver.badge.entity;

//Generated May 22, 2014 3:25:47 PM by Hibernate Tools 3.6.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Badge generated by hbm2java
 */
@Entity
@Table(name = "badge", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Badge implements java.io.Serializable {

	private Integer id;
	private String code;
	private Byte status;
	private String eligibility;
	private Set<Achievement> achievements = new HashSet<Achievement>(0);

	public Badge() {
	}

	public Badge(String code) {
		this.code = code;
	}

	public Badge(String code, Byte status, String eligibility,
			Set<Achievement> achievements) {
		this.code = code;
		this.status = status;
		this.eligibility = eligibility;
		this.achievements = achievements;
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

	@Column(name = "code", unique = true, nullable = false, length = 45)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "eligibility", length = 65535)
	public String getEligibility() {
		return this.eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "badge")
	public Set<Achievement> getAchievements() {
		return this.achievements;
	}

	public void setAchievements(Set<Achievement> achievements) {
		this.achievements = achievements;
	}

}