package com.fintrack.exception;

public class UserValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserValidateException(String message) {
		super(message);
	}
	
}
