package com.fintrack.model.expense;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExpenseRequestDTO {

	private Integer id;
	@NotBlank
	@Size(max = 100)
	private String name;
	@NotBlank
	private String type;
	@NotNull
	@Min(value = 1)
	private Integer dayMonthPayment;
	@NotNull
	@Min(value = 1)
	private Integer amountQuotas;
	@NotNull
	@Min(value = 1)
	private Double totalAmount;

	@Override
	public String toString() {
		return "ExpenseRequestDTO [id=" + id + ", name=" + name + ", type=" + type + ", dayMonthPayment="
				+ dayMonthPayment + ", amountQuotas=" + amountQuotas + ", totalAmount=" + totalAmount + "]";
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

	public Integer getDayMonthPayment() {
		return dayMonthPayment;
	}

	public void setDayMonthPayment(Integer dayMonthPayment) {
		this.dayMonthPayment = dayMonthPayment;
	}

	public Integer getAmountQuotas() {
		return amountQuotas;
	}

	public void setAmountQuotas(Integer amountQuotas) {
		this.amountQuotas = amountQuotas;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
