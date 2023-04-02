package com.fintrack.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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

}
