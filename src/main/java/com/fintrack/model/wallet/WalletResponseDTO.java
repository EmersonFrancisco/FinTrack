package com.fintrack.model.wallet;

import java.util.List;

import com.fintrack.enums.WalletType;
import com.fintrack.model.Deposit;

public class WalletResponseDTO {

	private Integer id;
	private String name;
	private WalletType type;
	private Double balance;
	private List<Deposit> deposits;

	public WalletResponseDTO() {
	}

	public WalletResponseDTO(Integer id, String name, WalletType type, Double balance, List<Deposit> deposits) {
		super();
		this.id = id;
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

	public WalletType getType() {
		return type;
	}

	public void setType(WalletType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "WalletResponseDTO [id=" + id + ", name=" + name + ", type=" + type + ", balance=" + balance
				+ ", deposits=" + deposits + "]";
	}

}
