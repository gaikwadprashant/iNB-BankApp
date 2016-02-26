package com.inbbank.exception;

public class InvalidUserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserException() {
		super("Invalid User.");
	}

	public InvalidUserException(String exceptionMsg) {
		super(exceptionMsg);
	}
}
