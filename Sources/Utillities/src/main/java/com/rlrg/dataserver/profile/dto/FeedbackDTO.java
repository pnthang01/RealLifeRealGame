package com.rlrg.dataserver.profile.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

@JsonDTO(singularName = "Feedback", pluralName = "Feedbacks")
public class FeedbackDTO {

	@JsonExport(name = "Id")
	private Integer id;

	@Length(min = 2, max = 30)
	@JsonExport(name = "Name")
	private String name;

	@Email
	@JsonExport(name = "Email")
	private String email;

	@Length(min = 2, max = 30)
	@JsonExport(name = "Title")
	private String title;

	@Length(min = 5, max = 500)
	@JsonExport(name = "Message")
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
