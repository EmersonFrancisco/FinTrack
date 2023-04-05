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
@Table(name = "re_credit_card_expense")
public class ReCreditCardExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_credit_card")
	@Basic(optional = false)
	private Integer idCreditCard;
	
	@Column(name = "id_expense")
	@Basic(optional = false)
	private Integer idExpense;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCreditCard() {
		return idCreditCard;
	}

	public void setIdCreditCard(Integer idCreditCard) {
		this.idCreditCard = idCreditCard;
	}

	public Integer getIdExpense() {
		return idExpense;
	}

	public void setIdExpense(Integer idExpense) {
		this.idExpense = idExpense;
	}
		
}
