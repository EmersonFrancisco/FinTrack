package com.fintrack.model.quota;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fintrack.enums.ExpenseStatus;
import com.fintrack.model.expense.Expense;

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
	@JsonIgnore
	@Basic(optional = false)
	private Expense expense;
	
	@Column(name = "amount")
	@Basic(optional = false)
	private Double amount;
	
	@Column(name = "total_paid")
	private Double totalPaid;
	
	@Column(name = "payment_date")
	@Basic(optional = false)
	private OffsetDateTime paymentDate;
	
	@Column(name = "status")
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private ExpenseStatus status;

	@Override
	public int hashCode() {
		return Objects.hash(amount, id, paymentDate, status, totalPaid);
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
				&& Objects.equals(paymentDate, other.paymentDate) && status == other.status && Objects.equals(totalPaid, other.totalPaid);
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(Double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public OffsetDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(OffsetDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public ExpenseStatus getStatus() {
		return status;
	}

	public void setStatus(ExpenseStatus status) {
		this.status = status;
	}
	
}
