package com.rlrg.dataserver.badge.entity;

// Generated May 22, 2014 3:25:47 PM by Hibernate Tools 3.6.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

/**
 * Achievement generated by hbm2java
 */
@Entity
@Table(name = "achievement", catalog = "rlrg")
public class Achievement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private User user;
	private Badge badge;
	private Date achivedTime;

	public Achievement() {
	}

	public Achievement(User user, Badge badge, Date achivedTime) {
		this.user = user;
		this.badge = badge;
		this.achivedTime = achivedTime;
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
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "badge_id", nullable = false)
	public Badge getBadge() {
		return this.badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "achived_time", nullable = false, length = 19)
	public Date getAchivedTime() {
		return this.achivedTime;
	}

	public void setAchivedTime(Date achivedTime) {
		this.achivedTime = achivedTime;
	}

}