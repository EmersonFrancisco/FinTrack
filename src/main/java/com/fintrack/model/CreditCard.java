package com.fintrack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Override
	public int hashCode() {
		return Objects.hash(createDate, id, name, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(createDate, other.createDate) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}
	
}
