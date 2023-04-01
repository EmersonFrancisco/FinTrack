package com.fintrack.enums;

public enum WalletType {
	
	BANK ("Conta Bancaria"),
	MONEY ("Dinheiro");
	
	private String label;
	
	private WalletType(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
