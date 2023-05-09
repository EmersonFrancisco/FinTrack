package com.fintrack.exception;

public class ExpenseValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExpenseValidateException(String message) {
		super(message);
	}
	
}
