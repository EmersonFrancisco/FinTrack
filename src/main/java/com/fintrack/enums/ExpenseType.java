package com.fintrack.enums;

public enum ExpenseType {
	
	FIXED ("Fixa"),
	FINANCED ("Parcelada"),
	CREDIT_CARD ("Cartão de crédito");
	
	private String label;
	
	private ExpenseType (String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
