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

import com.rlrg.dataserver.profile.entity.User;



/**
 * Achievement generated by hbm2java
 */
@Entity
@Table(name = "achievement")
public class Achievement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "badge_id", nullable = false)
	private Badge badge;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "achieved_time", nullable = false, length = 19)
	private Date achievedTime;

	public Achievement() {
	}

	public Achievement(User user, Badge badge, Date achievedTime) {
		this.user = user;
		this.badge = badge;
		this.achievedTime = achievedTime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Badge getBadge() {
		return this.badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	public Date getAchievedTime() {
		return achievedTime;
	}

	public void setAchievedTime(Date achievedTime) {
		this.achievedTime = achievedTime;
	}

}
