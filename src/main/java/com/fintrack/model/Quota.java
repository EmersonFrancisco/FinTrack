package com.fintrack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fintrack.enums.ExpenseStatus;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "quota")
public class Quota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_expense")
	private Expense expense;
	
	@Column(name = "quota_number")
	private Integer quotaNumber;
	
	@Column(name = "amount")
	private Float amount;
	
	@Column(name = "total_paid")
	private Float totalPaid;
	
	@Column(name = "payment_day")
	@Temporal(TemporalType.DATE)
	private Date paymentDay;
	
	@Column(name = "status")
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
	
}
