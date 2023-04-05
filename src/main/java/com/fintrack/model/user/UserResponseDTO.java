package com.fintrack.model.user;

import java.time.OffsetDateTime;

public class UserResponseDTO {

	private Integer id;
	private String name;
	private String surname;
	private String username;
	private OffsetDateTime createDate;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OffsetDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(OffsetDateTime createDate) {
		this.createDate = createDate;
	}

}
