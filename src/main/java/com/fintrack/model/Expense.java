package com.fintrack.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fintrack.enums.ExpenseStatus;
import com.fintrack.enums.ExpenseType;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "expense")
public class Expense implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
	private User user;
	
	@Column(name = "name")
	@Basic(optional = false)
	private String name;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	private ExpenseType type;
	
	@Column(name = "day_month_payment")
	private Integer dayMonthPayment;
	
	@OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Quota> quotas;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	private ExpenseStatus status;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@PrePersist
	private void prePersist() {
		createDate = new Date();
	}

	@Override
	public int hashCode() {
		return Objects.hash(createDate, dayMonthPayment, id, name, status, type, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return Objects.equals(createDate, other.createDate) && Objects.equals(dayMonthPayment, other.dayMonthPayment)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && status == other.status
				&& type == other.type && Objects.equals(user, other.user);
	}
	
}
