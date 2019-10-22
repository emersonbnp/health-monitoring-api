package com.healthmonitoringapi.exception;

public class EntityNotFoundException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8353793840152629291L;
	private static final String DEFAULT_MESSAGE = "Entity not found";
	
	public EntityNotFoundException(String string) {
		super(string);
	}

	public EntityNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

}
