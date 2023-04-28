package com.fintrack.model.wallet;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import com.fintrack.enums.WalletType;
import com.fintrack.model.Deposit;
import com.fintrack.model.user.User;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

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
	
	@NotBlank
	@Column(name = "name")
	@Basic(optional = false)
	private String name;
	
	@NotBlank
	@Column(name = "type")
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private WalletType type;
	
	@Column(name = "balance")
	@Basic(optional = false)
	private Double balance;
	
	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Deposit> deposits;
	
	@Column(name = "create_date")
	private OffsetDateTime createDate;
	
	@PrePersist
	private void prePersist() {
		createDate = OffsetDateTime.now();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WalletType getType() {
		return type;
	}

	public void setType(WalletType type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Deposit> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}

	public OffsetDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(OffsetDateTime createDate) {
		this.createDate = createDate;
	}
	
}
