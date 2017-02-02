package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown by {@link EmailNotification}.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public class EmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201L;
	
	private String message;
	
	public EmailException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}

}
