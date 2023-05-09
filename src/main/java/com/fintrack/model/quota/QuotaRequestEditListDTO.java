package com.fintrack.model.quota;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class QuotaRequestEditListDTO {

	@NotNull
	private Integer idExpense;
	@NotEmpty
	private List<QuotaRequestDTO> listQuotasRequest;

	@Override
	public String toString() {
		return "QuotaRequestEditListDTO [idExpense=" + idExpense + ", listQuotasRequest=" + listQuotasRequest + "]";
	}

	public Integer getIdExpense() {
		return idExpense;
	}

	public void setIdExpense(Integer idExpense) {
		this.idExpense = idExpense;
	}

	public List<QuotaRequestDTO> getListQuotasRequest() {
		return listQuotasRequest;
	}

	public void setListQuotasRequest(List<QuotaRequestDTO> listQuotas) {
		this.listQuotasRequest = listQuotas;
	}

	public Double getTotalAmountByListQuotasRequestDTO() {
		Double totalAmount = 0.0;
		for (QuotaRequestDTO quotaRequest : listQuotasRequest) {
			totalAmount += quotaRequest.getAmount();
		}
		return totalAmount;
	}

}
