package com.fintrack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fintrack.enums.WalletType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "wallet")
public class Wallet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
	private User user;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private WalletType type;
	
	@Column(name = "balance")
	private Float balance;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@PrePersist
	private void prePersist() {
		createDate = new Date();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(balance, createDate, id, name, type, user);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(createDate, other.createDate)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && type == other.type
				&& Objects.equals(user, other.user);
	}
	
}
