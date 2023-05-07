package com.fintrack.model.expense;

import java.time.OffsetDateTime;
import java.util.List;

import com.fintrack.enums.ExpenseType;
import com.fintrack.model.Quota;

public class ExpenseResponseDTO {

	private Integer id;
	private String name;
	private ExpenseType type;
	private Integer dayMonthPayment;
	private List<Quota> Quotas;
	private OffsetDateTime createDateTime;

	@Override
	public String toString() {
		return "ExpenseResponseDTO [id=" + id + ", name=" + name + ", type=" + type + ", dayMonthPayment="
				+ dayMonthPayment + ", Quotas=" + Quotas + ", createDateTime=" + createDateTime + "]";
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

	public ExpenseType getType() {
		return type;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}

	public Integer getDayMonthPayment() {
		return dayMonthPayment;
	}

	public void setDayMonthPayment(Integer dayMonthPayment) {
		this.dayMonthPayment = dayMonthPayment;
	}

	public List<Quota> getQuotas() {
		return Quotas;
	}

	public void setQuotas(List<Quota> quotas) {
		Quotas = quotas;
	}

	public OffsetDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(OffsetDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

}
