package com.fintrack.enums;

public enum ExpenseStatus {
	
	PENDENT ("Pendente"),
	PARTIAL_PAID_PENDENT ("Pago parcial e pendente"),
	PARTIAL_PAID_DELAYED ("Pago parcial e atrasado"),
	DELAYED ("Atrasado"),
	PAID ("Pago");
	
	private String label;
	
	private ExpenseStatus (String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
