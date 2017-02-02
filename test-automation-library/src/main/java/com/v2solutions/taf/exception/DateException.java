package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown by {@link DateUtil}.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public class DateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;
	
	private String message;
	
	
	public DateException(String message) {
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
