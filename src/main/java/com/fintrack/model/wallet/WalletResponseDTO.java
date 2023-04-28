package com.fintrack.model.wallet;

import java.util.List;

import com.fintrack.enums.WalletType;
import com.fintrack.model.Deposit;
import com.fintrack.model.user.User;

public class WalletResponseDTO {

	private User user;
	private String name;
	private WalletType type;
	private Double balance;
	private List<Deposit> deposits;

	public WalletResponseDTO() {
	}

	public WalletResponseDTO(User user, String name, WalletType type, Double balance, List<Deposit> deposits) {
		super();
		this.user = user;
		this.name = name;
		this.type = type;
		this.balance = balance;
		this.deposits = deposits;
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

	@Override
	public String toString() {
		return "WalletResponseDTO [user=" + user + ", name=" + name + ", type=" + type + ", balance=" + balance
				+ ", deposits=" + deposits + "]";
	}

}
