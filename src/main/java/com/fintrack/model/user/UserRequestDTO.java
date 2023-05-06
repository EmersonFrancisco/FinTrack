package com.fintrack.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

	@Size(max = 100)
	@NotBlank
	private String name;

	@Size(max = 100)
	@NotBlank
	private String surname;
	
	@Size(max = 100)
	@NotBlank
	@Email
	private String username;
	
	@Size(max = 100, min = 12)
	@NotBlank
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + "]";
	}

}
