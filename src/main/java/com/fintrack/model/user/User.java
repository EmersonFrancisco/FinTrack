package com.fintrack.model.user;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	@Basic(optional = false)
	@Size(max = 100)
	@NotBlank
	private String name;
	
	@Column(name = "surname")
	@Basic(optional = false)
	@Size(max = 100)
	@NotBlank
	private String surname;

	@Column(name = "username")
	@Basic(optional = false)
	@Size(max = 100)
	@NotBlank
	private String username;
	
	@Column(name = "password")
	@Basic(optional = false)
	@Size(max = 100)
	@NotBlank
	private String password;
	
	@Column(name = "create_date")
	private OffsetDateTime createDate;
	
	@Column(name = "auth_token")
	private String authToken;
	
	@PrePersist
	private void prePersist() {
		createDate = OffsetDateTime.now();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(authToken, createDate, id, name, password, surname);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(authToken, other.authToken) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public OffsetDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(OffsetDateTime createDate) {
		this.createDate = createDate;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
}
