package com.fintrack.model.quota;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;

public class QuotaRequestDTO {

	private Integer id;
	@NotNull
	private Double amount;
	@NotNull
	private OffsetDateTime paymentDate;

	@Override
	public String toString() {
		return "QuotaRequestDTO [id=" + id + ", amount=" + amount + ", paymentDate="
				+ paymentDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public OffsetDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(OffsetDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

}
