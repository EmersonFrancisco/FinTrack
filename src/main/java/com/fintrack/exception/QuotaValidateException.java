package com.fintrack.exception;

public class QuotaValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public QuotaValidateException(String message) {
		super(message);
	}
	
}
