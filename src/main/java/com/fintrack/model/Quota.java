package com.fintrack.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fintrack.enums.ExpenseStatus;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quota")
public class Quota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_expense")
	@Basic(optional = false)
	private Expense expense;
	
	@Column(name = "quota_number")
	@Basic(optional = false)
	private Integer quotaNumber;
	
	@Column(name = "amount")
	@Basic(optional = false)
	private Float amount;
	
	@Column(name = "total_paid")
	private Float totalPaid;
	
	@Column(name = "payment_day")
	@Basic(optional = false)
	private OffsetDateTime paymentDay;
	
	@Column(name = "status")
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private ExpenseStatus status;

	@Override
	public int hashCode() {
		return Objects.hash(amount, id, paymentDay, quotaNumber, status, totalPaid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quota other = (Quota) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(id, other.id)
				&& Objects.equals(paymentDay, other.paymentDay) && Objects.equals(quotaNumber, other.quotaNumber)
				&& status == other.status && Objects.equals(totalPaid, other.totalPaid);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Integer getQuotaNumber() {
		return quotaNumber;
	}

	public void setQuotaNumber(Integer quotaNumber) {
		this.quotaNumber = quotaNumber;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(Float totalPaid) {
		this.totalPaid = totalPaid;
	}

	public OffsetDateTime getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(OffsetDateTime paymentDay) {
		this.paymentDay = paymentDay;
	}

	public ExpenseStatus getStatus() {
		return status;
	}

	public void setStatus(ExpenseStatus status) {
		this.status = status;
	}
	
}
