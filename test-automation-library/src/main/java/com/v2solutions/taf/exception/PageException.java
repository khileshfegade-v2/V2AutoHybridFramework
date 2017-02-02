package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown by {@link DateUtil}.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class PageException extends Exception {
	
	
	private static final long serialVersionUID = 204L;
	
	private String message;
	
	public PageException(String message) {
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
