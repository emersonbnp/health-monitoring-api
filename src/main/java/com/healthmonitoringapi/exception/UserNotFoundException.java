package com.healthmonitoringapi.exception;

public class UserNotFoundException extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7294571802129998489L;
	private static final String DEFAULT_MESSAGE = "User not found";
	
	public UserNotFoundException(String string) {
		super(string);
	}

	public UserNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

}
