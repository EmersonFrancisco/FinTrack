package com.fintrack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "deposit")
public class Deposit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_user")
	@Basic(optional = false)
	private Integer idUser;
	
	@ManyToOne
	@JoinColumn(name = "id_wallet", referencedColumnName = "id")
	@Basic(optional = false)
	private Wallet wallet;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private Float amount;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@PrePersist
	private void prePersist() {
		createDate = new Date();
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, createDate, description, id, idUser, wallet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deposit other = (Deposit) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(idUser, other.idUser) && Objects.equals(wallet, other.wallet);
	}
	
}
