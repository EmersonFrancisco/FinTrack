package com.fintrack.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "re_payment_quota")
public class RePaymentQuota implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_payment")
	@Basic(optional = false)
	private Integer idPayment;
	
	@Column(name = "id_quota")
	@Basic(optional = false)
	private Integer idQuota;
	
	@Column(name = "amount")
	@Basic(optional = false)
	private Double amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(Integer idPayment) {
		this.idPayment = idPayment;
	}

	public Integer getIdQuota() {
		return idQuota;
	}

	public void setIdQuota(Integer idQuota) {
		this.idQuota = idQuota;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
