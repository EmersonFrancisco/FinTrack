package com.fintrack.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserBasicDTO {
	
	@NotNull
	private Integer id;

	@NotBlank
	@Size(max = 100)
	private String name;

	@NotBlank
	@Size(max = 100)
	private String surname;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserBasicDTO [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

}
