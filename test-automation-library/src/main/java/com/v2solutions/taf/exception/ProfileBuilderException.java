package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown if exception is raised during profile build.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class ProfileBuilderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 205L;
	
	private String message;
	
	public ProfileBuilderException(String message) {
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
